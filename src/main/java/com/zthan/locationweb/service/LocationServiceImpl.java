package com.zthan.locationweb.service;

import com.zthan.locationweb.entities.Location;
import com.zthan.locationweb.repo.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repo;

    @Override
    public Location saveLocation(Location location) {
        return repo.save(location);
    }

    @Override
    public void updateLocation(Location location) {
        repo.save(location);
    }

    @Override
    public void deleteLocation(Location location) {
        repo.delete(location);

    }

    @Override
    public Location getLocationById(int id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Location> getAllLocation() {
        return repo.findAll();
    }
}
