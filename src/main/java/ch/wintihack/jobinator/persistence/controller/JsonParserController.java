package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.JobDetail;
import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.persistence.repository.JobDetailRepository;
import ch.wintihack.jobinator.persistence.repository.JobPreviewRepository;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;

@RestController
@RequestMapping("jobs")
public class JsonParserController {

    private static final String FILE = "./src/main/java/ch/wintihack/jobinator/utilities/jobs-2019.json";
    private static final int MAX_VARCHAR_LENGTH = 1000;
    private static final int MAX_NUMBER_OF_JOBS = 5000;

    @Autowired
    private JobDetailRepository jobDetailRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void insertJobs() throws Exception {
        JobDetail job;
        BufferedReader fileReader = new BufferedReader(new FileReader(FILE));
        String line;
        int i = 0;
        while ((line = fileReader.readLine()) != null && i < MAX_NUMBER_OF_JOBS) {
            job = parseLine(line);
            if (job != null && job.getJobDetailId() != null) {
                jobDetailRepository.save(job);
            }
            i++;
        }
    }

    private JobDetail parseLine(String line) throws Exception {
        JSONObject jo = new JSONObject(line);
        JobDetail job = new JobDetail();
        JobPreview jobPreview = new JobPreview();

        int jobId = Integer.parseInt(jo.getString("id"));
        String title = jo.getString("title");
        String text = Jsoup.parse(jo.getString("content"))
                .text();
        if (text.length() > MAX_VARCHAR_LENGTH) {
            text = text.substring(0, MAX_VARCHAR_LENGTH);
        }
        String url = jo.getString("url");
        String location = jo.getString("location");

        job.setJobDetailId(jobId);
        job.setJobTitle(title);
        job.setJobText(text);
        job.setUrl(url);
        job.setLocation(location);

        jobPreview.setJobPreviewId(jobId);
        jobPreview.setJobTitle(title);

        String splitText;
        if (text.length() > 255) {
            splitText = text.substring(0, 255);
        } else {
            splitText = text;
        }

        jobPreview.setJobText(splitText);
        job.setJobPreview(jobPreview);
        return job;
    }
}