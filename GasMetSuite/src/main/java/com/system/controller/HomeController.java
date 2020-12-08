package com.system.controller;

import com.main.Main;
import com.system.entity.CostSmell;
import com.system.entity.Metriche1;
import com.system.entity.metricDescription;
import com.system.entity.metricDescriptionAll;
import com.system.service.MetricService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.objects.NativeMath.round;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.auth.Credentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

    @Autowired
    private MetricService metricService;
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String loginView(HttpServletRequest request, Model theModel) throws Exception {
        return "home";
    }

    @PostMapping("/fileupload")
    public String processUpload(Model theModel, @RequestParam(name = "file") MultipartFile file) throws Exception {
        Map<String, List> metriche1 = Main.getMetric(file.getName(), new String(file.getBytes(), Charset.forName("UTF-8")));
        Map<String, List> colorLine = new HashMap<String, List>();
        ArrayList<String> appq;
        ArrayList<metricDescriptionAll> app = new ArrayList<metricDescriptionAll>();
        ArrayList<Metriche1> app1 = new ArrayList<Metriche1>();
        appq = new ArrayList<String>();
        for (Map.Entry<String, List> entry : metriche1.entrySet()) {
            String key = entry.getKey();
            List values = entry.getValue();
            //   metricService.insertMetricDesc(new metricDescription(key.split("=")[0], "", key.split("=")[1]));
            List a = new ArrayList();
            if (!values.get(0).toString().equals("0.0")) {

                metricDescription met = metricService.findByMetric(key.split("=")[0]);
                System.out.println("metriche:"+met.toString());
                CostSmell cd = metricService.findByCostSmell(met.getCostSmell());

                for (int i = 1; i < values.size(); i++) {
                    appq.add(values.get(i).toString());
                    a.add(values.get(i).toString());

                }
                DecimalFormat df = new DecimalFormat("###.###");

                metricDescriptionAll mt = new metricDescriptionAll(met.getMetric(), met.getDescription(), cd.getDescription(), df.format(Double.parseDouble(values.get(0).toString())), a
                );

                colorLine.put(met.getMetric(), appq);
                app.add(mt);
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line = "";
        int i = 1;

        System.out.println("colorLinesize:" + colorLine.size());
        while ((line = br.readLine()) != null) {
            System.out.println("line:" + line);
            Metriche1 e = new Metriche1(StringEscapeUtils.escapeHtml4(line), " ", i);
            for (String d : appq) {
                if (Integer.parseInt(d) == i) {
                    e.setB("bgcolor=\"#FF0000\"");
                }
            }
            app1.add(e);
            i++;
        }

        
        ArrayList<String> app2 = new ArrayList<String>();
        for (Map.Entry<String, List> entry : colorLine.entrySet()) {
            System.out.println(entry.getKey());
            for (int j = 0; j < entry.getValue().size(); j++) {
                System.out.println(entry.getValue().get(j).toString());
                app2.add(entry.getValue().get(j).toString());
            }

        }

        //System.out.println("size;" + app3.size());
        theModel.addAttribute("metriche", app);
        theModel.addAttribute("file", app1);
        return "index";
    }

}
