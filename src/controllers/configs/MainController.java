<<<<<<< HEAD
package controllers.configs;

import controllers.CoursesController;
import controllers.RealController;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import controllers.exceptions.MappingNotFoundException;
import ui.AddCourseView;
import ui.View;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLEditorKit;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class MainController {
    public static final MainController MAIN_CONTROLLER = new MainController();
    private JFrame mainPage;
    private JEditorPane pane;
    private Class[] controllers = new Class[]{RealController.class, CoursesController.class};
    private View[] views = new View[]{new AddCourseView()};
    private HashMap<String, Method> linkedPaths;
    private HashMap<String, View> linkedViews;

    public MainController() {
        mainPage = new JFrame();
        mainPage.setLocationRelativeTo(null);
        mainPage.setSize(1000, 1000);
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        linkedPaths = new HashMap<>();
        linkedViews = new HashMap<>();
    }



    public void linkEverything(){
        for(Class controller:controllers){
            try {
                for(Method method:controller.getMethods()){
                    if(method.getAnnotation(RequestPath.class)!=null)
                        linkedPaths.put(method.getAnnotation(RequestPath.class).value(), method);
                }
            }catch (NullPointerException ex){
                ex.fillInStackTrace();
            }
        }
        for(View view:views){
            linkedViews.put(view.getClass().getAnnotation(RequestPath.class).value(),view);
        }
    }

    public void renderTemplate(String path, Map<String, Object> params){
        try {
            if(!linkedPaths.containsKey(path)){
                if(!linkedViews.containsKey(path)) throw new MappingNotFoundException(path);
                View view = linkedViews.get(path);
                view.renderView();
            } else {
                if(pane!=null) mainPage.remove(pane);
                Object controller = linkedPaths.get(path).getDeclaringClass().newInstance();
                Method m = linkedPaths.get(path);
                Model model = new Model();
                List<Object> methodParams = new LinkedList<>();
                Parameter[] requiredParams = m.getParameters();
                for (Parameter param : requiredParams) {
                    if (param.getType().equals(Model.class)) methodParams.add(model);
                    else {
                        String name = param.getAnnotation(RequestParam.class).value();
                        methodParams.add(param.getType().cast(params.get(name)));
                    }
                }
                String templateName = ((String) m.invoke(controller, methodParams.toArray()));
                if(templateName.startsWith("redirect:")) {
                    renderTemplate(templateName.substring(9), model.getAllParams());
                    return;
                }
                pane = new JEditorPane();
                pane.setEditable(false);
                pane.setContentType("text/html");
                pane.setText(new Processor().renderPage(templateName, model));
                ((HTMLEditorKit)pane.getEditorKit()).setAutoFormSubmission(false);
                mainPage.add(pane);
                pane.addHyperlinkListener(this::hyperlinkUpdate);
                mainPage.setVisible(true);
            }
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
                name = attributeNames.nextElement();
            }catch (Exception ex) {
                continue;
            }
            ret.put(name.toString(), attributes.getAttribute(name));
        }
        return ret;
    }

    private void hyperlinkUpdate(HyperlinkEvent e){
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            String pathToGo = "";
            if(e.getDescription()==null){
                Map<String, Object> formAttributes = getElementAttributes(e.getSourceElement());
                pathToGo = formAttributes.get("action").toString();
            }
            if(e.getDescription()!=null) pathToGo = e.getDescription();
            HashMap<String, Object> params = new HashMap<>();
            if(pathToGo.contains("?")){
                for(String pair: pathToGo.substring(pathToGo.indexOf("?")+1).split("&")){
                    params.put(pair.split("=")[0], pair.split("=")[1]);
                }
                pathToGo=pathToGo.substring(0, pathToGo.indexOf("?"));
            }
            renderTemplate(pathToGo, params);
        }
    }
}
=======
package controllers.configs;

import controllers.CoursesController;
import controllers.RealController;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import controllers.exceptions.MappingNotFoundException;
import controllers.exceptions.UnsatisfiedDependencyException;
import ui.AddCourseView;
import ui.View;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLEditorKit;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class MainController {
    private static MainController MAIN_CONTROLLER;
    private JFrame mainPage;
    private JEditorPane pane;
    private Class[] controllers = new Class[]{RealController.class, CoursesController.class};
    private View[] views = new View[]{new AddCourseView()};
    private HashMap<String, Method> linkedPaths;
    private HashMap<String, View> linkedViews;

    private HashMap<String, Object> instantiatedControllers;

    private MainController() throws UnsatisfiedDependencyException {
        mainPage = new JFrame();
        mainPage.setLocationRelativeTo(null);
        mainPage.setSize(1000, 1000);
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        linkedPaths = new HashMap<>();
        linkedViews = new HashMap<>();
        instantiatedControllers = new HashMap<>();
        instantiateControllers();
    }

    public static void createMainController() throws UnsatisfiedDependencyException {
        MAIN_CONTROLLER = new MainController();
    }

    public static MainController getMainController(){
        return MAIN_CONTROLLER;
    }


    public void linkEverything(){
        for(Class controller:controllers){
            try {
                for(Method method:controller.getMethods()){
                    if(method.getAnnotation(RequestPath.class)!=null)
                        linkedPaths.put(method.getAnnotation(RequestPath.class).value(), method);
                }
            }catch (NullPointerException ex){
                ex.fillInStackTrace();
            }
        }
        for(View view:views){
            linkedViews.put(view.getClass().getAnnotation(RequestPath.class).value(),view);
        }
    }

    public void renderTemplate(String path, Map<String, Object> params){
        try {
            if(!linkedPaths.containsKey(path)){
                if(!linkedViews.containsKey(path)) throw new MappingNotFoundException(path);
                View view = linkedViews.get(path);
                view.renderView();
            } else {
                if(pane!=null) mainPage.remove(pane);
                Method m = linkedPaths.get(path);
                Model model = new Model();
                List<Object> methodParams = new LinkedList<>();
                Parameter[] requiredParams = m.getParameters();
                for (Parameter param : requiredParams) {
                    if (param.getType().equals(Model.class)) methodParams.add(model);
                    else {
                        String name = param.getAnnotation(RequestParam.class).value();
                        if(param.getType().equals(int.class)||param.getType().equals(Integer.class)){
                            methodParams.add(Integer.parseInt(params.get(name).toString()));
                        }else if(param.getType().equals(long.class)||param.getType().equals(Long.class)){
                            methodParams.add(Long.parseLong(params.get(name).toString()));
                        }else if(param.getType().equals(double.class)||param.getType().equals(Double.class)){
                            methodParams.add(Double.parseDouble(params.get(name).toString()));
                        }else if(param.getType().equals(float.class)||param.getType().equals(Float.class)){
                            methodParams.add(Float.parseFloat(params.get(name).toString()));
                        }else {
                            methodParams.add(param.getType().cast(params.get(name)));
                        }
                    }
                }
                String templateName = ((String) m.invoke(instantiatedControllers.get(linkedPaths.get(path).getDeclaringClass().getName().toLowerCase()), methodParams.toArray()));
                if(templateName.startsWith("redirect:")) {
                    renderTemplate(templateName.substring(9), model.getAllParams());
                    return;
                }
                pane = new JEditorPane();
                pane.setEditable(false);
                pane.setContentType("text/html");
                pane.setText(new Processor().renderPage(templateName, model));
                ((HTMLEditorKit)pane.getEditorKit()).setAutoFormSubmission(false);
                mainPage.add(pane);
                pane.addHyperlinkListener(this::hyperlinkUpdate);
                mainPage.setVisible(true);
            }
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
                name = attributeNames.nextElement();
            }catch (Exception ex) {
                continue;
            }
            ret.put(name.toString(), attributes.getAttribute(name));
        }
        return ret;
    }

    private void hyperlinkUpdate(HyperlinkEvent e){
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            String pathToGo = "";
            if(e.getDescription()==null){
                Map<String, Object> formAttributes = getElementAttributes(e.getSourceElement());
                pathToGo = formAttributes.get("action").toString();
            }
            if(e.getDescription()!=null) pathToGo = e.getDescription();
            HashMap<String, Object> params = new HashMap<>();
            if(pathToGo.contains("?")){
                for(String pair: pathToGo.substring(pathToGo.indexOf("?")+1).split("&")){
                    params.put(pair.split("=")[0], pair.split("=")[1]);
                }
                pathToGo=pathToGo.substring(0, pathToGo.indexOf("?"));
            }
            renderTemplate(pathToGo, params);
        }
    }

    private void instantiateControllers() throws UnsatisfiedDependencyException {
        for(Class controller: controllers){
            Constructor constructor = controller.getConstructors()[0];
            List<Object> params = new ArrayList<>();
            for(Class parameter: constructor.getParameterTypes()) {
                params.add(ServicesDispatcher.getServicesDispatcher().getService(parameter.getName().toLowerCase()));
            }
            Object controllerInstance;
            try {
                controllerInstance = constructor.newInstance(params.toArray());
            }catch (Exception e){
                throw new UnsatisfiedDependencyException(controller.getName());
            }
            instantiatedControllers.put(controller.getName().toLowerCase(), controllerInstance);
        }
    }
}
>>>>>>> ui
