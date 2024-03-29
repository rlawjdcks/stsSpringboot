package com.sky;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sky.dto.Emp;
import com.sky.mapper.EmpMapper;

@SpringBootTest
class Sb03221ApplicationTests {
	
	@Autowired
	EmpMapper empMapper;

	
	@Test
	@DisplayName("emp 테이블 ")
	void select() {
		for (Emp dto : empMapper.selectEmpList()) {
			System.out.println(dto);
		}
	}
	
	@Test
    @DisplayName("emp 테이블에 데이터 삽입")
    void insertEmpData() {
        // 임의의 Emp 객체 생성
        Emp emp = Emp.builder()
                .empno(9998)
                .ename("John Doe")
                .job("Manager")
                .mgr(7839)
                .hiredate("2024-03-29")
                .sal(5000)
                .comm(1000)
                .deptno(10)
                .build();

        // 생성한 Emp 객체를 테이블에 삽입
        empMapper.insertBoard(emp);

        // 삽입된 데이터를 확인하기 위해 조회하여 출력
        System.out.println("Inserted Emp data: " + empMapper.selectEmpList());
    }
	
	
	@Test
	@DisplayName("Delete emp data")
	void deleteEmpData() {
	    int empnoToDelete = 100;

	    empMapper.deleteBoard(empnoToDelete);

	}
	
	@Test
	@DisplayName("Update emp data")
	void updateEmpData() {
	    // 업데이트할 Emp 객체 생성
	    Emp updatedEmp = Emp.builder()
	            .empno(9999) // 업데이트할 직원의 사번
	            .ename("Jane Doe") // 업데이트할 직원의 이름
	            .job("Director") // 업데이트할 직원의 직책
	            .mgr(7839) // 업데이트할 직원의 상사 사번
	            .hiredate("2024-03-29") // 업데이트할 직원의 입사일
	            .sal(7000) // 업데이트할 직원의 급여
	            .comm(1500) // 업데이트할 직원의 보너스
	            .deptno(20) // 업데이트할 직원의 부서번호
	            .build();

	    // Emp 데이터 업데이트
	    empMapper.updateBoard(updatedEmp);

	    // 업데이트된 데이터 확인을 위해 조회하여 출력
	    System.out.println("Updated Emp data: " + empMapper.selectEmpList());
	}
}