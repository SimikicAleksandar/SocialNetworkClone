import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Comment } from 'src/app/models/comment.model';
import { Group } from 'src/app/models/group.model';
import { Post } from 'src/app/models/post.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CommentService } from 'src/app/services/comment.service';
import { GroupService } from 'src/app/services/group.service';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit{
  posts: Post[] = [];
  newPostContent: string = '';
  currentUser: any;
  groups!: Group[];
  selectedGroup!: number;

  constructor(
    private postService: PostService,
    private authService: AuthenticationService,
    private groupService: GroupService,
    private commentService: CommentService){}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.loadPosts();

    this.groupService.getAll().subscribe(
      (data: Group[]) => {
        this.groups = data;
      },
      (error) => {
        console.log("Error: ", error);
      }
    )

  }

  showComments: boolean = false;

  toggleComments(post: Post) {
    post.showComments = !post.showComments;
    if (post.showComments) {
      this.loadComments(post);
    }
  }

  getGroupName(groupId: number): string {
    const group = this.groups.find(g => g.id === groupId);
    return group ? group.name : '';
  }

  createPost(){
    if(!this.newPostContent.trim()){
      return
    }

    this.currentUser = this.authService.getCurrentUser();
    if(!this.currentUser){
      console.log("nepoznat ulogovani korisnik");
      return
    }

    const newPost: Post = {
      content: this.newPostContent,
      containedBy: this.selectedGroup,
      comments: [],
      isEditing: false,
      isUpdating: false,
      updatedContent: '',
      showComments: false
    };

    this.postService.save(newPost).subscribe(
      createdPost => {
        this.posts.push(createdPost);
        this.loadPosts();
        this.newPostContent = '';
      },
      error => {
        console.log(error);
      }
    );
  }

  loadPosts(){
    this.postService.getAll().subscribe(
      posts => {
        this.posts = posts;
        for (const post of this.posts) {
          this.loadComments(post);
        }
        posts.reverse();
      },
      error => {
        console.log(error);
      }
    )
  }

  deletePost(postId: number){
    this.currentUser = this.authService.getCurrentUser();
    const post = this.posts.find(p => p.id === postId);

    if(!post || !this.currentUser || post.createdBy?.username !== this.currentUser.sub){
        console.error("Niste kreirali post, ne mozete da ga obrisete.");
        return
    }
    if (confirm("Are you sure you want to delete this post?")) {
      this.postService.delete(postId).subscribe(() => {
        this.loadPosts();
      });
    }

    this.loadPosts();
  }

  startEditPost(post: Post) {
    post.isEditing = true;
    post.updatedContent = post.content;
  }

  cancelEditPost(post: Post) {
    post.isEditing = false;
  }

  updatePost(postId: number, post: Post){
    this.currentUser = this.authService.getCurrentUser();

    if(!this.currentUser || post.createdBy?.username !== this.currentUser.sub){
      console.error("Niste kreirali objavu!");
      return
    }
    if (!post.updatedContent.trim()) {
      return;
    }

    post.content = post.updatedContent;
    post.isEditing = false;
    post.isUpdating = true;

    this.postService.update(postId, post.content).subscribe(
      updatePost =>{
        post.isUpdating = false;
      },
      error => {
        console.log("Greska: ", error);
        post.isEditing = true;
        post.isUpdating = false;
      }
    )
  }

  commentInput: string = '';

  loadComments(post: Post){
    if(post){
      this.commentService.getCommentsFromPost(post.id).subscribe(
        comments => {
          post.comments = comments
        },
        error => {
          console.error("Greska: ", error);
        }
      );
    }
  }

  addComment(postId: number, commentContent: string){
    const post = this.posts.find(p => p.id === postId);
    if(post){
      const newComment: Comment = {
        text: commentContent,
        updatedText: '',
        isUpdating: false
      };
      this.commentService.save(post.id, newComment).subscribe(
        createdComment => {
          post.comments.push(createdComment);
          this.commentInput = '';
        },
        error => {
          console.error("Greska: ", error);
        }
      );
    }
  }

  editComment(comment: Comment){
    comment.isEditing = true;
    comment.updatedText = comment.text;
  }

  updateComment(post: Post, commentId: number, comment: Comment){
    if(!comment.updatedText.trim()){
      return
    }

    comment.text = comment.updatedText;
    comment.isEditing = false;
    comment.isUpdating = true;

    this.commentService.update(commentId, comment.text).subscribe(
      updatedComment => {
        comment.isUpdating = false;
        this.loadComments(post);
      },
      error => {
        console.error("Greska: ", error);
        comment.text = comment.updatedText;
        comment.isEditing = true;
        comment.isUpdating = false;
      }
    );
  }

  cancelEditComment(comment: Comment){
    comment.isEditing = false;
  }

  deleteComment(post: Post, commentId: number){
    const comment = post.comments.find(c => c.id === commentId);

    if(confirm("Da li zelis da obrises komentar")){
      this.commentService.delete(commentId).subscribe(() =>{
        this.loadComments(post);
      });
    }
  }
}
