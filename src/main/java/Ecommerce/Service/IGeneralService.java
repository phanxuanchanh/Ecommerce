package Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce.DTO.Analyst;

@Service
interface IGeneralService {
	@Autowired

	Analyst GetAnalyst();
}

