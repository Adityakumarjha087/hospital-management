package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;

@Controller
@RequestMapping("/patients")
public class PatientController {
    
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

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
    public String savePatient(@Valid @ModelAttribute("patient") Patient patient, 
                            BindingResult result,
                            Model model) {
        logger.debug("Received patient data: {}", patient);
        
        if (result.hasErrors()) {
            logger.error("Validation errors: {}", result.getAllErrors());
            return "patient/form";
        }
        
        try {
            if (patient.getId() == null) {
                logger.debug("Creating new patient");
                patientService.createPatient(patient);
            } else {
                logger.debug("Updating existing patient with id: {}", patient.getId());
                patientService.updatePatient(patient.getId(), patient);
            }
            return "redirect:/patients";
        } catch (Exception e) {
            logger.error("Error saving patient: ", e);
            model.addAttribute("error", "Error saving patient: " + e.getMessage());
            return "patient/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Patient patient = patientService.getPatientById(id);
            model.addAttribute("patient", patient);
            return "patient/form";
        } catch (Exception e) {
            logger.error("Error fetching patient: ", e);
            return "redirect:/patients";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return "redirect:/patients";
        } catch (Exception e) {
            logger.error("Error deleting patient: ", e);
            return "redirect:/patients";
        }
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