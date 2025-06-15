package com.pds.curiousmind.persistence.adapter.interfaces;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.IAdapter;

import java.util.List;

public interface ICourseAdapter extends IAdapter<Course> {

    List<Course> findByName(String name);
}

