import { Component, OnInit } from '@angular/core';
import { Question , QuestionOption } from '../_models/index';

import { AddQuestionService } from '../_services/index';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  questions : Question[] = [];

  addQuestionModel : Question;
  addOptionModel : QuestionOption[] = [];
  option : string = "";

  constructor(private questionService : AddQuestionService) { }

  ngOnInit() {
  	this.questions = [{
  		"id": 1,
  		"questionDesc": "Who is PM ?",
  		options: [
  			{"id":1, "optionDesc": "Modi"},
  			{"id":2, "optionDesc": "Rahul"},
  			{"id":3, "optionDesc": "Kohli"},
  			{"id":4, "optionDesc": "Dhoni"}
  		]
  	}
  	];
    this.addQuestionModel = new Question();
    
  }

  addQuestion() {
    console.log("add question called");
    this.addQuestionModel.options.push(...this.addOptionModel);
    this.questions.push(this.addQuestionModel);
    this.questionService.addQuestion(this.addQuestionModel).subscribe(
      (question : Question) => {
       console.log(question);
       },
      err => console.error(err),
      () => console.log('Question added successful')
    );
    
    this.addQuestionModel = new Question();
    this.addOptionModel = [];
  }

  addOption() {
    this.addOptionModel.push({"optionDesc" : this.option});
    this.option = "";
  }

  removeOptoin(index) {
    this.addOptionModel.splice(index, 1);
  }

}
