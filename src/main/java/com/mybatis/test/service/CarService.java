package com.mybatis.test.service;

import java.util.List;

import com.mybatis.test.repository.CarRepository;
import com.mybatis.test.vo.CarVO;

public class CarService {

	private CarRepository ur = new CarRepository();
	
	public List<CarVO> getCars(CarVO car){
		return ur.selectCars(car);
	}
	
	public CarVO getCar(int ciNum) {
		return ur.selectCar(ciNum);
	}
	
	public int insertCar(CarVO car) {
		return ur.insertCar(car);
	}
	
	public int updateCar(CarVO car) {
		return ur.updateCar(car);
	}
	
	public int deleteCar(CarVO car) {
		return ur.deleteCar(car);
	}
}
