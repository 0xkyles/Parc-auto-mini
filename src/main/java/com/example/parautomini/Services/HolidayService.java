package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.HolidayReqDTO;
import com.example.parautomini.DTOs.Requests.HolidayUpdateReq;
import com.example.parautomini.DTOs.Response.HolidayDTO;
import com.example.parautomini.Entites.Holiday;
import com.example.parautomini.Exceptions.APIException;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.HolidayMapper;
import com.example.parautomini.Repositories.DriverRepository;
import com.example.parautomini.Repositories.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayService implements IHolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;
    private final DriverRepository driverRepository;

    @Override
    public List<HolidayDTO> getAll() {
        return holidayRepository.findAll().stream().map(holidayMapper::toDTO).toList();
    }

    @Override
    public List<HolidayDTO> get(int driverId) {
        List<Holiday> holidays = holidayRepository.findAllByDriverId(driverId);

        return holidays.stream().map(holidayMapper::toDTO).toList();
    }

    @Override
    public void add(HolidayReqDTO holidayReqDTO) {
        int driverId = holidayReqDTO.getDriverId();
        var driver = driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", Integer.toString(driverId))
        );
        Date startDate = holidayReqDTO.getStartDate();
        Date endDate = holidayReqDTO.getEndDate();

        if(startDate.after(endDate)) {
            throw new APIException("Start date has to be before the end date of the holiday", HttpStatus.BAD_REQUEST);
        }
        if(startDate.before(new Date())) {
            throw new APIException("Start date has to be in the future", HttpStatus.BAD_REQUEST);
        }
        var holiday = holidayMapper.objToEntity(holidayReqDTO);
        holiday.setDriver(driver);
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);
        holiday.setAddedDate(new Date());
        holidayRepository.save(holiday);
    }

    @Override
    public void delete(int holidayId) {
        var holiday = holidayRepository.findById(holidayId).orElseThrow(
                () -> new ResourceNotFoundException("Holiday", "id", Integer.toString(holidayId))
        );

        holidayRepository.delete(holiday);
    }

    @Override
    public void deleteAll(int driverId) {
        var driver = driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", Integer.toString(driverId))
        );

        holidayRepository.deleteByDriver(driver);
    }

    @Override
    public void update(int holidayId, HolidayUpdateReq holiday) {
        Date startDate = holiday.getStartDate();
        Date endDate = holiday.getEndDate();
        if(startDate.after(endDate)) {
            throw new APIException("Start date has to be before the end date of the holiday", HttpStatus.BAD_REQUEST);
        }

        var h = holidayRepository.findById(holidayId).orElseThrow(
                () -> new ResourceNotFoundException("Holiday", "id", Integer.toString(holidayId))
        );
        if(startDate.before(h.getAddedDate())) {
            throw new APIException("Start date has to be valid (before the originally affected date", HttpStatus.BAD_REQUEST);
        }

        h.setEndDate(endDate);
        h.setStartDate(startDate);
        holidayRepository.save(h);
    }
}
