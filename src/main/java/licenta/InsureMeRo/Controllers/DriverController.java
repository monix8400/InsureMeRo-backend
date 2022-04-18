package licenta.InsureMeRo.Controllers;

import licenta.InsureMeRo.Models.Driver;
import licenta.InsureMeRo.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/driver")
public class DriverController {
    private final DriverService driverService;

    // standard constructors, dependency injection
    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/getDrivers")
    public List<Driver> getDrivers() {
        return driverService.getDrivers();
    }

    @PostMapping("/addDriver")
    public void addDriver(@RequestBody Driver driver) {
        driverService.addDriver(driver);
    }

    @GetMapping("/getDriverById/{id}")
    public Driver getDriverById(@PathVariable("id") Long id) {
        return driverService.getDriverById(id).orElse(null);
    }

    @DeleteMapping("/deleteDriverById/{id}")
    public void deleteDriverById(@PathVariable("id") Long id) {
        driverService.deleteDriver(id);
    }

    //update

}
