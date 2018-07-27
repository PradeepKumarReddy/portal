import { Role } from './role';
import { UserRegister } from './userRegister';
export class User {
    id: number;
    username: string;
    password: string;
    enabled: boolean;
    userRegister: UserRegister;
}
