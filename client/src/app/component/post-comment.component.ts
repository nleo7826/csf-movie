import { Component } from '@angular/core';
import { MovieReviewService } from '../service/movie-review.service';
import { Subscription } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from '../model/review';
import { Comment } from '../model/comment';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent {
  form!: FormGroup;
  queryParams$! :  Subscription;
  movieParam!: any;
  movieName! : string;
  movies! : Review[];

  constructor(private activatedRoute: ActivatedRoute,  private formBuilder: FormBuilder,
    private mrSvc: MovieReviewService, private router: Router){

  }

  ngOnInit(): void {
    this.form = this.createForm();
    this.queryParams$ = this.activatedRoute.queryParams.subscribe(
      (queryParams) => {
        this.movieName = queryParams['movieName'];
      }
    );

  }

  saveComment(){
    const commentFormVal = this.form?.value['comment'];
    const c = {} as Comment;
    c.comment = commentFormVal;
    c.title = this.movieName;

    this.mrSvc.saveComment(c);
    this.previous();
  }

  async previous(){
    const l = await this.mrSvc
    .getReviews(this.movieName);
    this.movies = l;
  }

  ngOnDestroy(): void{
    this.queryParams$.unsubscribe();
  }

  private createForm(): FormGroup{
    return this.formBuilder.group({
      comment : this.formBuilder.control(''),  
    })
  }
}
