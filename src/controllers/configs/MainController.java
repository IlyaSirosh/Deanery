package controllers.configs;

import controllers.exceptions.MappingNotFoundException;
import controllers.RealController;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class MainController {
    JEditorPane pane = null;
    private JFrame mainPage;
    private Class[] controllers = new Class[]{RealController.class};
    private HashMap<String, Method> linkedPaths;

    public MainController() {
        mainPage = new JFrame();
        mainPage.setLocationRelativeTo(null);
        mainPage.setSize(1000, 1000);
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        linkedPaths = new HashMap<>();
    }



    public void linkEverything(){
        for(Class controller:controllers){
            try {
                for(Method method:controller.getMethods()){
                    if(method.getAnnotation(RequestPath.class)!=null)
                        linkedPaths.put(method.getAnnotation(RequestPath.class).value()+"%"+method.getAnnotation(RequestPath.class).method(), method);
                }
            }catch (NullPointerException ex){
                ex.fillInStackTrace();
            }
        }
    }

    public void renderTemplate(String path, RequestMethods method, Map<String, Object> params){
        try {
            if(!linkedPaths.containsKey(path+"%"+method)) throw new MappingNotFoundException(path, method);
            Object controller = linkedPaths.get(path+"%"+method).getDeclaringClass().newInstance();
            Method m = linkedPaths.get(path+"%"+method);
            Model model = new Model();
            List<Object> methodParams = new LinkedList<>();
            Parameter[] requiredParams = m.getParameters();
            for(Parameter param: requiredParams){
                if(param.getType().equals(Model.class)) methodParams.add(model);
                else{
                    String name = param.getAnnotation(RequestParam.class).value();
                    methodParams.add(param.getType().cast(params.get(name)));
                }
            }
            String templateName = ((String) m.invoke(controller, methodParams.toArray()));
            Processor processor = new Processor();
            pane = new JEditorPane();
            pane.setEditable(false);
            pane.setContentType("text/html");
            pane.setText(processor.renderPage(templateName, model));
//            HTMLEditorKit kit = new HTMLEditorKit();
//            pane.setEditorKit(kit);
//            kit.setAutoFormSubmission(false);
            mainPage.add(pane);
            pane.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        System.out.println(e.getDocument().getText(e.getDocument().getStartPosition().getOffset(), e.getDocument().getEndPosition().getOffset()));
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        System.out.println(e.getDocument().getText(e.getDocument().getStartPosition().getOffset(), e.getDocument().getEndPosition().getOffset()));
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    try {
                        System.out.println(e.getDocument().getText(e.getDocument().getStartPosition().getOffset(), e.getDocument().getEndPosition().getOffset()));
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            pane.addHyperlinkListener(this::hyperlinkUpdate);
            mainPage.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<String, Object> getParamsFromForm(Element form){
        Map<String, Object> ret = new HashMap<>();
        for(int i =0; i<form.getElementCount(); i++) {
            Element inputElement = form.getElement(i);
            if(!inputElement.getName().equals("input")) continue;
            Map<String, Object> inputAttributes = getElementAttributes(inputElement);
            if(inputAttributes.containsKey("type") && inputAttributes.get("type").equals("submit") || !inputAttributes.containsKey("name") || !inputAttributes.containsKey("value")) continue;
            ret.put(inputAttributes.get("name").toString(), inputAttributes.get("value"));
        }
        return ret;
    }

    private Map<String, Object> getElementAttributes(Element element){
        Map<String, Object> ret = new HashMap<>();
        AttributeSet attributes = element.getAttributes();
        Enumeration attributeNames = attributes.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            Object name = null;
            try {
                name = (HTML.Attribute) attributeNames.nextElement();
            }catch (Exception ex) {
                continue;
            }
            ret.put(name.toString(), attributes.getAttribute(name));
        }
        return ret;
    }

    private void hyperlinkUpdate(HyperlinkEvent e){
//        System.out.println(pane.getText());
        System.out.println(((FormView)e.getSource()).getElement().getParentElement().getParentElement().getName());
//        System.out.println(((HTMLDocument.BlockElement)((FormView)e.getSource()).getElement().getParentElement().getParentElement().getElement(0).getElement(0)).get);
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            Map<String, Object> formAttributes = getElementAttributes(e.getSourceElement());
            String pathToGo = formAttributes.get("action").toString();
            RequestMethods methodToGo = RequestMethods.valueOf(formAttributes.get("method").toString().toUpperCase());
            Map<String, Object> requestParams = getParamsFromForm(e.getSourceElement().getElement(0));
            renderTemplate(pathToGo, methodToGo, requestParams);
        }
    }

    public enum RequestMethods{
        GET, POST
    }
}
