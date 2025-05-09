package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping
    public String createPatient(@Valid @ModelAttribute("patient") Patient patient, 
                              BindingResult result) {
        if (result.hasErrors()) {
            return "patient/form";
        }
        patientService.createPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patient/form";
    }

    @PostMapping("/{id}")
    public String updatePatient(@PathVariable Long id, 
                              @Valid @ModelAttribute("patient") Patient patient,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "patient/form";
        }
        patientService.updatePatient(id, patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    // REST endpoints
    @GetMapping("/api")
    @ResponseBody
    public Iterable<Patient> getAllPatientsApi() {
        return patientService.getAllPatients();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Patient getPatientApi(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Patient createPatientApi(@Valid @RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Patient updatePatientApi(@PathVariable Long id, @Valid @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deletePatientApi(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
} 