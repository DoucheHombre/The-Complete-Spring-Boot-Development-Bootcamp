package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

	List<Grade> studentGrades = new ArrayList<Grade>();

	@GetMapping("/grades")
	public String getGrades(Model model) {
		model.addAttribute("grades", studentGrades);
		return "grades";
	}

	@GetMapping("/")
	public String gradeForm(Model model, @RequestParam(required = false) String name) {
		System.out.println(name);
		int position = indexOfDataToBeUpdated(name);
		if (position != -1000) {
			model.addAttribute("grade", studentGrades.get(position));
		} else {
			model.addAttribute("grade", new Grade());
		}
		return "form";
	}

	@PostMapping("/handleSubmit")
	public String submitForm(Grade grade) {
		System.out.println(grade);
		int position = indexOfDataToBeUpdated(grade.getName());
		if (position != -1000) {
			studentGrades.set(position, grade);
		} else {
			studentGrades.add(grade);
		}
		return "redirect:/grades";
	}

	public int indexOfDataToBeUpdated(String name) {
		for (int i = 0; i < studentGrades.size(); i++) {
			if (studentGrades.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1000;
	}
}
