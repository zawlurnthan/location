package com.zthan.locationweb.controllers;

import com.zthan.locationweb.entities.Location;
import com.zthan.locationweb.repo.LocationRepository;
import com.zthan.locationweb.service.LocationService;
import com.zthan.locationweb.utility.ReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {

    @Autowired
    LocationService service;

    @Autowired
    LocationRepository repo;

    @Autowired
    ReportUtil reportUtil;

    @Autowired
    ServletContext sc;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/showCreate")
    public String showCreate() {
        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, Model model) {
        Location locationSaved = service.saveLocation(location);
        String msg = "Location saved with id: " + locationSaved.getId();
        model.addAttribute("msg", msg);
        return "createLocation";
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(Model model) {
        List<Location> locations = service.getAllLocation();
        model.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("id") int id, Model model) {
//        Location location = service.getLocationById(id);
        Location location = new Location();
        location.setId(id);
        service.deleteLocation(location);
        List<Location> locations = service.getAllLocation();
        model.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") int id, Model model) {
        Location location = service.getLocationById(id);
        model.addAttribute("location", location);
        return "updateLocation";
    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute("location") Location location, Model model) {
        service.updateLocation(location);
        List<Location> locations = service.getAllLocation();
        model.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/generateReport")
    public String generateReport() {
        String path = sc.getRealPath("/");
        List<Object[]> data = repo.findTypeAndTypeCount();
        reportUtil.generatePieChart(path, data);
        return "report";
    }
}
