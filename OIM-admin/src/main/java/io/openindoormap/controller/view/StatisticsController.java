package io.openindoormap.controller.view;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.openindoormap.domain.statistics.StatisticsMonth;
import io.openindoormap.domain.statistics.StatisticsrYear;
import io.openindoormap.service.StatisticsService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService service;

    @GetMapping(value = {"/threedimension", "/userdata", "/transformation", "/access"})
    public String threedimension(HttpServletRequest request, Integer from, Integer to, Model model) {
        List<StatisticsrYear> l = new ArrayList<>();;

        if (from != null && to != null && from <= to) {
            int count = (to - from + 1);
            StatisticsMonth years = StatisticsMonth.builder().year(String.valueOf(from)).count(count * 12).build();
            List<StatisticsMonth> months = service.getStatisticsDataInfo(years);
            convertMonth2Year(l, months);
        } else if (to == null) {
            to = LocalDate.now().get(ChronoField.YEAR);
        }

        model.addAttribute("list", l);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        return "/statistics/threedimension";
    }

    @GetMapping(value = "/threedimension", produces = "application/json")
    public ResponseEntity<List<StatisticsrYear>> threedimensionJson(HttpServletRequest request, Integer from, Integer to, Model model) {
        List<StatisticsrYear> l = new ArrayList<>();;

        if (from != null && to != null && from <= to) {
            int count = (to - from + 1);
            StatisticsMonth years = StatisticsMonth.builder().year(String.valueOf(from)).count(count * 12).build();
            List<StatisticsMonth> months = service.getStatisticsDataInfo(years);
            convertMonth2Year(l, months);
        }
        model.addAttribute("list", l);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        return ResponseEntity.ok(l);
    }


    private void convertMonth2Year(List<StatisticsrYear> l, List<StatisticsMonth> months) {
        String preYear = "";
        int colIndex = 0;
        StatisticsrYear year = null;
        String month[] = null;

        for (int i = 0; i < months.size(); i++) {
            StatisticsMonth d = months.get(i);
            if (!preYear.equals(d.getYear())) {
                year = new StatisticsrYear();
                preYear = d.getYear();
                year.setYear(preYear);
                l.add(year);
                month = year.getMonth();
                colIndex = 0;
            }
            month[colIndex] = String.valueOf(d.getCount());
            colIndex ++;
        }
    }


    @GetMapping(value = "/userdata", produces = "application/json")
    public ResponseEntity<List<StatisticsrYear>> userdata(HttpServletRequest request, Integer from, Integer to, Model model) {
        List<StatisticsrYear> l = new ArrayList<>();;

        if (from != null && to != null && from <= to) {
            int count = (to - from + 1);
            StatisticsMonth years = StatisticsMonth.builder().year(String.valueOf(from)).count(count * 12).build();
            List<StatisticsMonth> months = service.getStatisticsUploadData(years);
            convertMonth2Year(l, months);
        }
        model.addAttribute("list", l);
        model.addAttribute("from", from);
        model.addAttribute("to", to);

        return ResponseEntity.ok(l);
    }

    @GetMapping(value = "/transformation", produces = "application/json")
    public ResponseEntity<List<StatisticsrYear>> transformation(HttpServletRequest request, Integer from, Integer to, Model model) {
        List<StatisticsrYear> l = new ArrayList<>();;

        if (from != null && to != null && from <= to) {
            int count = (to - from + 1);
            StatisticsMonth years = StatisticsMonth.builder().year(String.valueOf(from)).count(count * 12).build();
            List<StatisticsMonth> months = service.getStatisticsConverter(years);
            convertMonth2Year(l, months);
        }
        model.addAttribute("list", l);
        model.addAttribute("from", from);
        model.addAttribute("to", to);

        return ResponseEntity.ok(l);
    }

    @GetMapping(value = "/access", produces = "application/json")
    public ResponseEntity<List<StatisticsrYear>> accessLogs(HttpServletRequest request, Integer from, Integer to, Model model) {
        List<StatisticsrYear> l = new ArrayList<>();;

        if (from != null && to != null && from <= to) {
            int count = (to - from + 1);
            StatisticsMonth years = StatisticsMonth.builder().year(String.valueOf(from)).count(count * 12).build();
            List<StatisticsMonth> months = service.getStatisticsAccess(years);
            convertMonth2Year(l, months);
        }
        model.addAttribute("list", l);
        model.addAttribute("from", from);
        model.addAttribute("to", to);

        return ResponseEntity.ok(l);
    }

}
