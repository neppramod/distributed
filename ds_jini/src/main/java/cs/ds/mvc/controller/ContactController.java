package cs.ds.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs.ds.domain.Contact;
import cs.ds.mvc.service.interfaces.ContactService;

@Controller
@RequestMapping("/contacts/*")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("contactList", contactService.findAll());

		return "contacts/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("contact", new Contact());

		return "contacts/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("contact") Contact contact) {

		contactService.create(contact);

		return "redirect:/contacts/";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Contact contact = contactService.findById(id);

		model.addAttribute("contact", contact);

		return "contacts/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") Long id,
			@ModelAttribute("contact") Contact contact) {	

		contactService.update(contact);

		return "redirect:/contacts/";
	}

	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Contact contact = contactService.findById(id);

		model.addAttribute("contact", contact);

		return "contacts/view";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		contactService.delete(id);

		return "redirect:/contacts/";
	}

}
