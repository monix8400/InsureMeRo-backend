package licenta.InsureMeRo.controllers;

import licenta.InsureMeRo.models.Vehicle;
import licenta.InsureMeRo.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    // standard constructors, dependency injection
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/getVehicles")
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @PostMapping("/addVehicle")
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/getVehicleById/{id}")
    public Vehicle getVehicleById(@PathVariable("id") Long id) {
        return vehicleService.getVehicleById(id).orElse(null);
    }

    @DeleteMapping("/deleteVehicleById/{id}")
    public void deleteVehicleById(@PathVariable("id") Long id) {
        vehicleService.deleteVehicle(id);
    }

    //update

}

