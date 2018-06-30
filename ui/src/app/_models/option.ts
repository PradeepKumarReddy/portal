export class QuestionOption {
	id? : number;
	optionDesc? : string;
	answer? : boolean;
	selected? : boolean = false;
	userSelect?: boolean;
	/*
	Option(id : number,	name : string, isAnswer : boolean,	selected : boolean) {
		this.id = id;
		this.name = name;
		this.isAnswer = isAnswer;
		this.selected = selected;
	}
	*/
}