package com.cookies.demo.repo;

import com.cookies.demo.model.Viewer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by akshay on 4/2/20
 *
 * @author akshay
 * ViewerRepo
 */
public interface ViewerRepo extends CrudRepository<Viewer,Integer> {
    Viewer findByViewerId(String id);
}
