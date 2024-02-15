package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.TourPackage;
import com.app.service.TourPackageService;


@RestController
@RequestMapping("/admin")
public class TourPackageController {

	@Autowired
	private TourPackageService tourPackageService;

	@PostMapping("/register")
	public ResponseEntity<Object> addTourPackage(@RequestBody TourPackage tourPackage) {
		try {
			TourPackage newTourPackage = tourPackageService.addTourPackage(tourPackage);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("TourPackage registered successfully with busname : " + newTourPackage.getBusName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not register tour package");
		}

	}

	@GetMapping("/records")
	public ResponseEntity<Object> showTourPackages() {
		try {
			List<TourPackage> packages = tourPackageService.getTourPackages();
			return ResponseEntity.status(HttpStatus.OK).body(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("could not found resource");
		}

	}

	@PutMapping("/records/{id}")
	public ResponseEntity<Object> updateTourPackage(@PathVariable int id, @RequestBody TourPackage tourPackage) {
		try {
			TourPackage newTourPackage = tourPackageService.updateTourPackage(tourPackage, id);
			System.out.println(newTourPackage.getBusName());
			if (newTourPackage != null)
				return ResponseEntity.status(HttpStatus.OK).body("Tour package updated succcessfully");
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found resource");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found resource.....");
		}

	}

	@DeleteMapping("/records/{id}")
	public ResponseEntity<Object> deleteTourPackage(@PathVariable int id) {
		try {
		tourPackageService.deleteTourPackage(id);
		return ResponseEntity.status(HttpStatus.OK).body("Tour package deleted succcessfully");
		}
		catch (Exception e){
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found resource.....");
		}

	}

}
