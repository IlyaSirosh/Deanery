package controllers.configs;

import org.w3c.dom.*;
import resources.Resources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.util.Map;

public class Processor {
    private Map<String, Object> parameters;
    private enum CustomTags{
        FOR_EACH("pp:foreach", true), IF("pp:if", true), VALUE("pp:value",true, "value"), TEXT("pp:text",true), HREF("pp:href",true, "href"),
        ITEM("pp:item",false), INDEX("pp:index",false);
        String code, attrName;
        boolean coreTag;
        CustomTags(String s, boolean coreTag){
            this.code = s;
            this.coreTag=coreTag;
        }
        CustomTags(String s, boolean coreTag, String attrName){
            this.code = s;
            this.coreTag=coreTag;
            this.attrName = attrName;
        }
        static CustomTags isCustomTag(String s){
            for(CustomTags tag:CustomTags.values()){
                if(tag.coreTag&&tag.code.equals(s)) return tag;
            }
            return null;
        }
        String getAttrName(){
            return attrName;
        }
    }
    /**
     *  Method takes a HTML file and processes all the special tags by replacing them with parameters or other constructions. Result is returned as string ready to be shown
     * @param sourceFile - an HTML file that represents html file to be processed
     * @return a processed pure html file in a string
     */
    private String renderPage(File sourceFile, Map<String, Object> params){
        parameters = params;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(sourceFile);
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getDocumentElement().getChildNodes();
            for(int child=0; child<nodes.getLength();child++){
                processElement(nodes.item(child));
            }
            return generateString(doc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    String renderPage(String sourceFile, Model params){
        return renderPage(new File(getClass()
                        .getClassLoader()
                        .getResource(getTemplateName(sourceFile))
                        .getFile()),
                params.getAllParams());
    }

    private String getTemplateName(String templateName) {
        return Resources.templatePrefix + templateName + Resources.templateSuffix;
    }

    private void processElement(Node element){
        NamedNodeMap attributes = element.getAttributes();
        if(attributes!=null){
            if(attributes.getLength()==0){
                for (Node n = element.getFirstChild(); n != null; n = n.getNextSibling()) {
                    processElement(n);
                }
            }else
            for (int attrId=0;attrId<attributes.getLength();attrId++) {
                Node item = attributes.item(attrId);
                if (item != null) {
                    CustomTags tag = CustomTags.isCustomTag(item.getNodeName());
                    if (tag != null) {
                        processCustomAttribute(element, tag);
                    } else {
                        for (Node n = element.getFirstChild(); n != null; n = n.getNextSibling()) {
                            processElement(n);
                        }
                    }
                }
            }
        }
    }

    private void processCustomAttribute(Node element, CustomTags tag){
        switch (tag){
            case FOR_EACH:
                processForEach(element);
                break;
            case TEXT:
                processText(element);
                break;
            default:
                processAttribute(element, tag);
                break;
        }
    }

    private void processText(Node originalNode){
        NamedNodeMap attributes = originalNode.getAttributes();
        if(attributes!=null){
            originalNode.setTextContent(processExpression(attributes.getNamedItem(CustomTags.TEXT.code).getNodeValue()));
            attributes.removeNamedItem(CustomTags.TEXT.code);
        }
    }

    private void processAttribute(Node originalNode, CustomTags tag){
        NamedNodeMap attributes = originalNode.getAttributes();
        if(attributes!=null){
            ((Element)originalNode).setAttribute(tag.getAttrName(),processExpression(attributes.getNamedItem(tag.code).getNodeValue()));
            attributes.removeNamedItem(tag.code);
        }
    }

    private void processForEach(Node originalNode){
        String listName, paramName, indexName;
        NamedNodeMap attrs = originalNode.getAttributes();
        listName = attrs.getNamedItem(CustomTags.FOR_EACH.code).getNodeValue();
        paramName = attrs.getNamedItem(CustomTags.ITEM.code).getNodeValue();
        indexName = attrs.getNamedItem(CustomTags.INDEX.code).getNodeValue();
        Iterable list = (Iterable) parameters.get(listName);
        attrs.removeNamedItem(CustomTags.FOR_EACH.code);
        attrs.removeNamedItem(CustomTags.INDEX.code);
        attrs.removeNamedItem(CustomTags.ITEM.code);
        final int[] index = {0};
        list.forEach(item->{
            parameters.put(paramName,item);
            parameters.put(indexName, index[0]);
            Node tempNode = originalNode.cloneNode(true);
            processElement(tempNode);
            originalNode.getParentNode().insertBefore(tempNode,originalNode);
            index[0]++;
        });
        originalNode.getParentNode().removeChild(originalNode);
        parameters.remove(paramName); parameters.remove(indexName);
    }

    private String processExpression(String expression){
        if(expression.contains("~")){
            String res = expression.substring(0, expression.indexOf('~'));
            expression=expression.substring(expression.indexOf('~')+1);
            res+=evalExpression(expression.substring(0, expression.indexOf('~')));
            expression=expression.substring(expression.indexOf('~')+1);
            res+=expression;
            return res;
        }
        return parameters.get(expression).toString();
    }

    public String evalExpression(String exp){
        if(exp.indexOf('.')!=-1){
            Object obj = parameters.get(exp.substring(0, exp.indexOf('.')));
            java.lang.reflect.Method method;
            try {
                String paramName=exp.substring(exp.indexOf('.')+1);
                method = obj.getClass().getMethod(createGetterName(paramName));
                return method.invoke(obj).toString();
            } catch (Exception e) { e.printStackTrace(); }
        } else {
            return parameters.get(exp).toString();
        }
        return null;
    }

    private String createGetterName(String paramName){
        return "get"+paramName.substring(0,1).toUpperCase()+paramName.substring(1);
    }

    private String generateString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }
}
