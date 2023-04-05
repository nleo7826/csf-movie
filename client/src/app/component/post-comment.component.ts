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
  movieId!: string;
  movies! : Review[];

  constructor(private activatedRoute: ActivatedRoute,  private formBuilder: FormBuilder,
    private mrSvc: MovieReviewService, private router: Router){

  }

  ngOnInit(): void {
    this.form = this.createForm();
    this.queryParams$ = this.activatedRoute.queryParams.subscribe(
      (queryParams) => {
        this.movieParam = queryParams['movieParam'].split('|');
        console.log(this.movieParam[0]);
        console.log(this.movieParam[1]);
        this.movieName = this.movieParam[0];
        this.movieId = this.movieParam[1];
      }
    );

  }

  saveComment(){
    const commentFormVal = this.form?.value['comment'];
    const c = {} as Comment;
    c.comment = commentFormVal;
    c.id = this.movieId;

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
