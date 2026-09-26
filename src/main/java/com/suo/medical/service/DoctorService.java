package com.suo.medical.service;

import com.suo.medical.DTO.DoctorDTO;
import com.suo.medical.VO.DoctorVO;
import com.suo.medical.entity.Doctor;
import com.suo.medical.tool.PageUtil;

public interface DoctorService {

    PageUtil<Doctor> getPageDoctor(String name, Long current, Long size);

    Doctor adddoctorDTO(DoctorDTO doctorDTO);

    DoctorDTO updateDoctor(DoctorDTO doctorDTO);

    DoctorDTO getByIdDoctor(Long id);

    Boolean deleteDoctor(Long id);

    DoctorVO getDoctorVOById(Long id);
}
