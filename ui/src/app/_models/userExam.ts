import { UserResponse } from './userResponse';
export class UserExam {
id?: number;
username?: string;
examId?: number;
userResponses?: UserResponse[] = [];
}
