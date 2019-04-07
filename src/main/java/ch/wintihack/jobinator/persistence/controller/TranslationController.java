package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.Answer;
import ch.wintihack.jobinator.model.JobDetail;
import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.model.Question;
import ch.wintihack.jobinator.persistence.repository.AnswerRepository;
import ch.wintihack.jobinator.persistence.repository.JobDetailRepository;
import ch.wintihack.jobinator.persistence.repository.JobPreviewRepository;
import ch.wintihack.jobinator.persistence.repository.QuestionRepository;
import ch.wintihack.jobinator.persistence.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("translation")
public class TranslationController {
    TranslationService translationService = new TranslationService();
    @Autowired
    private QuestionRepository questionService;
    @Autowired
    private JobDetailRepository jobDetailRepository;
    @Autowired
    private JobPreviewRepository jobPreviewRepository;
    @Autowired
    private AnswerRepository answerRepository;


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/previews/{previewId}/{lang}")
    public JobPreview translateJob(@PathVariable(value = "lang") String lang, @PathVariable(value = "previewId") Integer previewId) throws Exception {
        JobPreview jobPreview = jobPreviewRepository.findById(previewId).orElseThrow(Exception::new);
        TranslationService translationService = new TranslationService();
        jobPreview.setJobTitle(translationService.translateTo(jobPreview.getJobTitle(), lang));
        jobPreview.setJobText(translationService.translateTo(jobPreview.getJobText(), lang));
        return jobPreview;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/detail/{detailId}/{lang}")
    public JobDetail translateDetail(@PathVariable(value = "lang") String lang, @PathVariable(value = "detailId") Integer detailId) throws Exception {
        JobDetail jobDetail = jobDetailRepository.findById(detailId).orElseThrow(Exception::new);
        TranslationService translationService = new TranslationService();
        jobDetail.setJobTitle(translationService.translateTo(jobDetail.getJobTitle(), lang));
        jobDetail.setJobText(translationService.translateTo(jobDetail.getJobText(), lang));
        return jobDetail;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/questions/{questionId}/{lang}")
    public Question translateQuestion(@PathVariable(value = "lang") String lang, @PathVariable(value = "questionId") Integer questionId) throws Exception {
        Question question = questionService.findById(questionId).orElseThrow(Exception::new);
        TranslationService translationService = new TranslationService();
        question.setQuestion(translationService.translateTo(question.getQuestion(), lang));
        return question;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/answers/{answer}/{lang}")
    public Answer translateAnswer(@PathVariable(value = "lang") String lang, @PathVariable(value = "answerId") Integer answerId) throws Exception {
        Answer answer = answerRepository.findById(answerId).orElseThrow(Exception::new);
        TranslationService translationService = new TranslationService();
        answer.setAnswerText(translationService.translateTo(answer.getAnswerText(), lang));
        return answer;
    }
}
