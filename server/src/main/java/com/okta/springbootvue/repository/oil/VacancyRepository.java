package com.okta.springbootvue.repository.oil;

import com.okta.springbootvue.entity.oil.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface VacancyRepository extends JpaRepository<Vacancy, Long> {
	Vacancy findById(long id);
}