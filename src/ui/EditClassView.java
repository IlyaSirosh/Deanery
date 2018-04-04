package ui;

import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;

import java.util.Map;

@RequestPath("/editClass")
public class EditClassView extends View{
    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {

    }
}