package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GlobalSuperstoreController {

	List<Item> listOfItems = new ArrayList<Item>();

	@GetMapping("/")
	public String getForm(Model model, @RequestParam(required = false) String id) {
		System.out.println("ID = " + id);
		model.addAttribute("categories", Constants.CATEGORIES);
		int index = itemIndex(id);
		if (index != Constants.NOT_FOUND) {
			model.addAttribute("item", listOfItems.get(index));
		} else {
			model.addAttribute("item", new Item());
		}
		return "form";
	}

	@GetMapping("/inventory")
	public String getInventory(Model model) {
		model.addAttribute("items", listOfItems);
		return "inventory";
	}

	@PostMapping("/submitItem")
	public String handleSubmit(Item item, RedirectAttributes redirectAttributes) {
		int index = itemIndex(item.getId());
		String status = Constants.SUCCESS_STATUS;
		if (index == Constants.NOT_FOUND) {
			listOfItems.add(item);
		} else if (within5Days(item.getDate(), listOfItems.get(index).getDate())) {
			listOfItems.set(index, item);
		} else {
			status = Constants.FAILED_STATUS;
		}

		redirectAttributes.addFlashAttribute("status", status);
		return "redirect:/inventory";
	}

	public int itemIndex(String id) {
		for (int i = 0; i < listOfItems.size(); i++) {
			if (listOfItems.get(i).getId().equals(id)) {
				return i;
			}
		}
		return Constants.NOT_FOUND;
	}

	public boolean within5Days(Date newDate, Date oldDate) {
		long diff = Math.abs(newDate.getTime() - oldDate.getTime());
		return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
	}

}
