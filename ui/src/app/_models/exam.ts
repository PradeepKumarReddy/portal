import {Question} from './question';
export class Exam {
id?: number;
examName?: string;
examDescription?: string;
examDate?: Date;
active?: boolean;
questions?: Question[];
}
