package com.example.parautomini;

import com.example.parautomini.Entites.LicenseType;
import com.example.parautomini.Entites.VehicleCategory;
import com.example.parautomini.Entites.VehiclePermitTypeMapping;
import com.example.parautomini.Entites.VehicleType;
import com.example.parautomini.Enums.LicenseTypeEnum;
import com.example.parautomini.Enums.VehicleCategoryEnum;
import com.example.parautomini.Enums.VehicleTypeEnum;
import com.example.parautomini.Keys.VehicleLicenseMappingKey;
import com.example.parautomini.Repositories.LicenseTypeRepository;
import com.example.parautomini.Repositories.VehicleCategoryRepository;
import com.example.parautomini.Repositories.VehicleLicenseTypeMappingRepository;
import com.example.parautomini.Repositories.VehicleTypeRepository;
import com.example.parautomini.Services.UserService;
import com.example.parautomini.utilities.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ParcAutoMiniApplication implements CommandLineRunner {
	@Autowired
	private LicenseTypeRepository licenseTypeRepository;
	@Autowired
	private VehicleCategoryRepository vehicleCategoryRepository;

	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;

	@Autowired
	private VehicleLicenseTypeMappingRepository v_l_typeMappingRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParcAutoMiniApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.addLicencesTypes();
		this.addVehicleCategories();
		this.addVehicleTypes();
		this.addVehicleAndLicenceTypeMapping();
	}

	private void addVehicleAndLicenceTypeMapping() {
		if(v_l_typeMappingRepository.findAll().isEmpty()) {
			List<LicenseType> license_types = licenseTypeRepository.findAll();

			for (var l_type : license_types) {
				var v_types = new ArrayList<VehicleType>();

				switch(l_type.getCategory()) {
					case A:
						v_types.add(vehicleTypeRepository.findByLabel("L_MOTORCYCLE").get());
						v_types.add(vehicleTypeRepository.findByLabel("L_MOPED").get());
						v_types.add(vehicleTypeRepository.findByLabel("L_SCOOTER").get());
						break;

					case B:
						v_types.add(vehicleTypeRepository.findByLabel("M_SMALL_CAR").get());
						v_types.add(vehicleTypeRepository.findByLabel("M_MEDIUM_CAR").get());
						v_types.add(vehicleTypeRepository.findByLabel("M_LARGE_CAR").get());
						v_types.add(vehicleTypeRepository.findByLabel("M_SUV").get());
						v_types.add(vehicleTypeRepository.findByLabel("N_LIGHT_TRUCK").get());
						v_types.add(vehicleTypeRepository.findByLabel("L_MOTORCYCLE").get());
						v_types.add(vehicleTypeRepository.findByLabel("L_MOPED").get());
						v_types.add(vehicleTypeRepository.findByLabel("L_SCOOTER").get());
						break;

					case C:
						v_types.add(vehicleTypeRepository.findByLabel("N_MEDIUM_TRUCK").get());
						v_types.add(vehicleTypeRepository.findByLabel("N_HEAVY_TRUCK").get());
						v_types.add(vehicleTypeRepository.findByLabel("T_AGRICULTURAL").get());
						v_types.add(vehicleTypeRepository.findByLabel("T_CONSTRUCTION").get());
						break;

					case D:
						v_types.add(vehicleTypeRepository.findByLabel("M_LARGE_CAR").get());
						v_types.add(vehicleTypeRepository.findByLabel("M_MEDIUM_CAR").get());
						v_types.add(vehicleTypeRepository.findByLabel("M_SUV").get());
						v_types.add(vehicleTypeRepository.findByLabel("N_LIGHT_TRUCK").get());
						v_types.add(vehicleTypeRepository.findByLabel("N_MEDIUM_TRUCK").get());
						v_types.add(vehicleTypeRepository.findByLabel("N_HEAVY_TRUCK").get());
						v_types.add(vehicleTypeRepository.findByLabel("T_AGRICULTURAL").get());
						v_types.add(vehicleTypeRepository.findByLabel("T_CONSTRUCTION").get());
						break;
				}

				for (var v_type : v_types) {
					var mapping = new VehiclePermitTypeMapping();
					var id = new VehicleLicenseMappingKey();
					id.setLicenseTypeId(l_type.getLicenseTypeId());
					id.setVehicleTypeId(v_type.getVehicleTypeId());
					mapping.setId(id);
					mapping.setLicenseType(l_type);
					mapping.setVehicleType(v_type);
					v_l_typeMappingRepository.save(mapping);
				}
			}
		}

	}

	private void addLicencesTypes() {
		if(licenseTypeRepository.findAll().isEmpty()) {
			for (LicenseTypeEnum type : LicenseTypeEnum.values()) {
				var licenseType = new LicenseType();
				licenseType.setCategory(type);
				licenseTypeRepository.save(licenseType);
			}
		}
	}

	private void addVehicleTypes() {
		if(vehicleTypeRepository.findAll().isEmpty()) {
			for (var type : VehicleTypeEnum.values()) {
				var v_type = new VehicleType();
				switch(type) {
					case M_SMALL_CAR:
					case M_MEDIUM_CAR:
					case M_LARGE_CAR:
					case M_SUV:
						v_type.setVehicleCategory(vehicleCategoryRepository.findByLabel(VehicleCategoryEnum.M_PASSENGER.name()).get());
						break;
					case N_HEAVY_TRUCK:
					case N_MEDIUM_TRUCK:
					case N_LIGHT_TRUCK:
						v_type.setVehicleCategory(vehicleCategoryRepository.findByLabel(VehicleCategoryEnum.N_GOODS.name()).get());
						break;
					case L_MOPED:
					case L_MOTORCYCLE:
					case L_SCOOTER:
						v_type.setVehicleCategory(vehicleCategoryRepository.findByLabel(VehicleCategoryEnum.L_2_3_WHEEL.name()).get());
						break;
					case T_AGRICULTURAL:
					case T_CONSTRUCTION:
						v_type.setVehicleCategory(vehicleCategoryRepository.findByLabel(VehicleCategoryEnum.T_TRACTOR.name()).get());
						break;
				}

				v_type.setLabel(type);
				vehicleTypeRepository.save(v_type);
			}
		}
	}

	private void addVehicleCategories() {
		if(vehicleCategoryRepository.findAll().isEmpty()) {
			Triple<Integer, String, VehicleCategoryEnum>[] entries = new Triple[4];
			entries[0] = new Triple<>(1, "Vehicles for carrying passengers", VehicleCategoryEnum.M_PASSENGER);
			entries[1] = new Triple<>(2, "Vehicles for carrying goods", VehicleCategoryEnum.N_GOODS);
			entries[2] = new Triple<>(3, "2- and 3-wheel vehicles and quadricycles", VehicleCategoryEnum.L_2_3_WHEEL);
			entries[3] = new Triple<>(4, "Agricultural and forestry tractors and their trailers", VehicleCategoryEnum.T_TRACTOR);

			for (var entry : entries) {
				var category = new VehicleCategory();
				category.setVehicleCategoryId(entry.getFirst());
				category.setDescription(entry.getSecond());
				category.setLabel(entry.getThird());
				vehicleCategoryRepository.save(category);
			}
		}
	}
}
