package com.italoghiele.projetointegrador.service;

import com.italoghiele.projetointegrador.dto.ActivityRequest;

import com.italoghiele.projetointegrador.dto.response.ActivityResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ActivityService {

    ActivityResponse create(@NotNull @RequestBody ActivityRequest request);

    List<ActivityResponse> findAllActivities();

    List<ActivityResponse> findByUser();
}
