package com.example.questtion_service.service;


import com.example.questtion_service.dao.QuestionDao;
import com.example.questtion_service.model.Question;
import com.example.questtion_service.model.QuestionWrapper;
import com.example.questtion_service.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionSerivce {

    private final static Logger logger = LoggerFactory.getLogger(QuestionSerivce.class);

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failour", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        List<Integer> questions = questionDao.findRandomQuestionByCategory(categoryName,numQuestion);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }
        for(Question q : questions){
            questionWrappers.add(new QuestionWrapper(q.getId(),
                    q.getQuestiontitle(),q.getOption1(),
                    q.getOption2(),q.getOption3(),
                    q.getOption4()));
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int result =0;
        for(Response r : responses){
            Question question = questionDao.findById(r.getId()).get();
            if(r.getResponse().equals(question.getCorrectoption()))
                result++;
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
