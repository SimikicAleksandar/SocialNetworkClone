import { Post } from "./post.model";

export class User {
    public id?: number;
    public username: string = "";
    public password: string = "";
    public email: string = "";
    public firstName: string = "";
    public lastName: string = "";
    public image: string = "";
    public friends: User[] = [];
    public displayName: string = "";
    public description: string = "";
    public registrationDate: Date = new Date();
    public posts: Post[] = [];
}
