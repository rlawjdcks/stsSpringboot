package com.sky.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sky.dto.Dept;
import com.sky.dto.Emp;

@Controller
public class MyController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String root(Model model) {
		List<Dept> list = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
		while (rs.next()) {
			Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname")).loc(rs.getString("loc"))
					.build();
			list.add(dept);
		}
		model.addAttribute("list", list);
		return "root";
	}

	@GetMapping("/selectEmp")
	public String selectEmp(Model model, @RequestParam("deptno") int deptno) {
		List<Emp> list2 = new ArrayList<>();
		SqlRowSet rs2 = jdbcTemplate.queryForRowSet(
				"select empno, ename, job, mgr, hiredate, sal, comm, deptno from emp where deptno = ?", deptno);
		while (rs2.next()) {
			Emp emp = Emp.builder().empno(rs2.getInt("empno")).ename(rs2.getString("ename")).job(rs2.getString("job"))
					.mgr(rs2.getInt("mgr")).hiredate(rs2.getString("hiredate")).sal(rs2.getInt("sal"))
					.comm(rs2.getInt("comm")).deptno(rs2.getInt("deptno")).build();
			list2.add(emp);
		}
		List<Dept> list = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
		while (rs.next()) {
			Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname")).loc(rs.getString("loc"))
					.build();
			list.add(dept);
		}
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		// 실제 뷰의 이름으로 수정
		return "root";
	}

	@GetMapping("/updateForm")
	public String updateForm(Model model, @RequestParam(name = "empno", required = false) Integer empno,
			@RequestParam(name = "ename", required = false) String ename, @RequestParam("deptno") int deptno) {
		if (empno == null && ename == null) {
			return "root"; // 기본 수정 폼의 이름으로 수정
		}
		List<Emp> list3 = new ArrayList<>();
		SqlRowSet rs3 = jdbcTemplate.queryForRowSet(
				"SELECT empno, ename, job, mgr, sal, comm, deptno FROM emp WHERE empno = ? OR ename = ?", empno, ename);
		while (rs3.next()) {
			Emp emp = Emp.builder().empno(rs3.getInt("empno")).ename(rs3.getString("ename")).job(rs3.getString("job"))
					.mgr(rs3.getInt("mgr")).sal(rs3.getInt("sal")).comm(rs3.getInt("comm")).deptno(rs3.getInt("deptno"))
					.build();
			list3.add(emp);
		}
		List<Emp> list2 = new ArrayList<>();
		SqlRowSet rs2 = jdbcTemplate.queryForRowSet(
				"select empno, ename, job, mgr, hiredate, sal, comm, deptno from emp where deptno = ?", deptno);
		while (rs2.next()) {
			Emp emp = Emp.builder().empno(rs2.getInt("empno")).ename(rs2.getString("ename")).job(rs2.getString("job"))
					.mgr(rs2.getInt("mgr")).hiredate(rs2.getString("hiredate")).sal(rs2.getInt("sal"))
					.comm(rs2.getInt("comm")).deptno(rs2.getInt("deptno")).build();
			list2.add(emp);
		}
		List<Dept> list = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
		while (rs.next()) {
			Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname")).loc(rs.getString("loc"))
					.build();
			list.add(dept);
		}
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		// 실제 뷰의 이름으로 수정
		return "root"; // 수정 폼의 이름으로 수정
	}

	@PostMapping("/input")
	public String modifyMember(@RequestParam("empno") int empno, @RequestParam("ename") String ename,
	                           @RequestParam("job") String job, @RequestParam("mgr") int mgr,
	                           @RequestParam("sal") int sal,
	                           @RequestParam("comm") int comm, @RequestParam("deptno") int deptno) {
	    String updateQuery = new StringBuilder()
	            .append("UPDATE emp SET ")
	            .append("ename = ?, ")
	            .append("job = ?, ")
	            .append("mgr = ?, ")
	            .append("sal = ?, ")
	            .append("comm = ?, ")
	            .append("deptno = ? ")
	            .append("WHERE empno = ?")
	            .toString();

	    jdbcTemplate.update(updateQuery, ename, job, mgr, sal, comm, deptno, empno);

	    return "redirect:/"; // 회원 목록 페이지로 리다이렉트
	}
}