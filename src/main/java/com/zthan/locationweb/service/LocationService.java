package com.zthan.locationweb.service;

import com.zthan.locationweb.entities.Location;
import java.util.List;
import java.util.Optional;


public interface LocationService {
    Location saveLocation(Location location);
    void updateLocation(Location location);
    void deleteLocation(Location location);
    Location getLocationById(int id);
    List<Location> getAllLocation();

}
