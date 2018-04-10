package com.controllers.configs;

import com.controllers.exceptions.EvaluatingExpression;
import org.w3c.dom.*;
import com.resources.Resources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.List;
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
    private String renderPage(String sourceFile, Map<String, Object> params){
        parameters = params;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getClass().getResourceAsStream(sourceFile));
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
        return renderPage(getTemplateName(sourceFile),
                params.getAllParams());
    }

    private String getTemplateName(String templateName) {
        return Resources.templatePrefix + templateName + Resources.templateSuffix;
    }

    private void processElement(Node element) throws EvaluatingExpression {
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

    private void processCustomAttribute(Node element, CustomTags tag) throws EvaluatingExpression {
        switch (tag){
            case FOR_EACH:
                processForEach(element);
                break;
            case TEXT:
                processText(element);
                break;
            case IF:
                processIf(element);
                break;
            default:
                processAttribute(element, tag);
                break;
        }
    }

    private void processIf(Node originalNode){
        try {
            NamedNodeMap attributes = originalNode.getAttributes();
            if (attributes != null) {
                if(processExpression(attributes.getNamedItem(CustomTags.IF.code).getNodeValue())==null) originalNode.getParentNode().removeChild(originalNode);
                else return;
            }
        }catch (EvaluatingExpression e){
            System.out.println("while processing pp:text on tag "+originalNode.getNodeName());
        }
    }

    private void processText(Node originalNode){
        try {
            NamedNodeMap attributes = originalNode.getAttributes();
            if (attributes != null) {
                originalNode.setTextContent(processExpression(attributes.getNamedItem(CustomTags.TEXT.code).getNodeValue()).toString());
                attributes.removeNamedItem(CustomTags.TEXT.code);
            }
        }catch (EvaluatingExpression e){
            System.out.println("while processing pp:text on tag "+originalNode.getNodeName());
        }
    }

    private void processAttribute(Node originalNode, CustomTags tag){
        try {
            NamedNodeMap attributes = originalNode.getAttributes();
            if (attributes != null) {
                ((Element) originalNode).setAttribute(tag.getAttrName(), processExpression(attributes.getNamedItem(tag.code).getNodeValue()).toString());
                attributes.removeNamedItem(tag.code);
            }
        }catch (EvaluatingExpression e){
            System.out.println("while processing tag ("+tag+") "+originalNode.getNodeName());
        }
    }

    private void processForEach(Node originalNode) throws EvaluatingExpression {
        String listName, paramName, indexName;
        NamedNodeMap attrs = originalNode.getAttributes();
        listName = attrs.getNamedItem(CustomTags.FOR_EACH.code).getNodeValue();
        paramName = attrs.getNamedItem(CustomTags.ITEM.code).getNodeValue();
        indexName = attrs.getNamedItem(CustomTags.INDEX.code).getNodeValue();
        Iterable list = (Iterable) processExpression(listName);
        attrs.removeNamedItem(CustomTags.FOR_EACH.code);
        attrs.removeNamedItem(CustomTags.INDEX.code);
        attrs.removeNamedItem(CustomTags.ITEM.code);
        final int[] index = {0};
        list.forEach(item->{
            parameters.put(paramName,item);
            parameters.put(indexName, index[0]);
            Node tempNode = originalNode.cloneNode(true);
            try {
                processElement(tempNode);
            } catch (EvaluatingExpression evaluatingExpression) {
                evaluatingExpression.printStackTrace();
            }
            originalNode.getParentNode().insertBefore(tempNode,originalNode);
            index[0]++;
        });
        originalNode.getParentNode().removeChild(originalNode);
        parameters.remove(paramName); parameters.remove(indexName);
    }

    private Object processExpression(String expression) throws EvaluatingExpression {
        String originalExpression = expression;
        try {
            if (expression.contains("~")) {
                String preExpression = expression.substring(0, expression.indexOf('~'));
                Object res =  evalExpression(expression.substring(expression.indexOf('~'),expression.lastIndexOf('~')+1));
                String afterExpression = expression.substring(expression.lastIndexOf('~') + 1);
                if(preExpression.length()>0 || afterExpression.length()>0) return preExpression+res.toString()+afterExpression;
                return res;
            }
            return expression;
        } catch (EvaluatingExpression e){
            System.out.println("In expression: "+originalExpression);
            throw new EvaluatingExpression();
        }
    }

    private Object evalExpression(String exp) throws EvaluatingExpression {
        StringBuilder answer = new StringBuilder();
        String[] expressions = exp.split("~");
        for(int i=1;i<expressions.length;i++){
            if(i%2==1){
                if(expressions.length==2) return getObject(expressions[i]);
                answer.append(getObject(expressions[i]).toString());
            }else {
                answer.append(expressions[i]);
            }
        }
        return answer.toString();
    }

    private Object getObject(String exp) throws EvaluatingExpression {
        String objectToGet = (exp.indexOf('.')==-1)?exp:exp.substring(0, exp.indexOf('.'));
        try {
            Object obj = parameters.get(objectToGet);
            if (exp.indexOf('.') == -1) return obj;
            else return continueEvaluating(obj, exp.substring(exp.indexOf('.')+1));
        }catch (Exception e){
            System.out.println("Exception while evaluating "+exp+":");
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement.toString());
            }
            throw new EvaluatingExpression();
        }
    }
    private Object continueEvaluating(Object obj, String toEval) throws Exception {
        String paramToEval = (toEval.indexOf('.') == -1) ? toEval : toEval.substring(0, toEval.indexOf('.'));
        Method method = obj.getClass().getMethod(createGetterName(paramToEval));
        if(toEval.indexOf('.') == -1) return method.invoke(obj);
        else return continueEvaluating(method.invoke(obj), toEval.substring(toEval.indexOf('.')+1));
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
