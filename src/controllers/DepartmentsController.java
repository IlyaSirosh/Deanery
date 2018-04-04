package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Department;
import model.Teacher;
import services.DepartmentService;
import services.TeacherService;

@Controller
public class DepartmentsController {
    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @RequestPath("/showDepartments")
    public String renderView(Model m){
        m.addParam("departments", departmentService.getAll());
        return "departments";
    }

    @RequestPath("/saveDepartment")
    public String saveDepartment(Model m, @RequestParam("department") Department department){
        departmentService.create(department);
        m.addParam("action", "persist");
        return "departmentChanged";
    }

    @RequestPath("/updateDepartment")
    public String updateDepartment(Model m, @RequestParam("department") Department department){
        departmentService.update(department);
        m.addParam("action", "update");
        return "departmentChanged";
    }

    @RequestPath("/deleteDepartment")
    public String deleteDepartment(Model m, @RequestParam("id") int departmentId){
        departmentService.delete(new Department(departmentId));
        m.addParam("action", "delete");
        return "departmentChanged";
    }

    @RequestPath("/detailsDepartment")
    public String deleteDepartment(Model m, @RequestParam("department") Department department){
        m.addParam("department", department);
        department.getTeachers().forEach(System.out::println);
        m.addParam("teachers", department.getTeachers());
        return "detailsDepartment";
    }
}
