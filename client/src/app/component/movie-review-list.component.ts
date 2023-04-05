import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Review } from '../model/review';
import { MovieReviewService } from '../service/movie-review.service';

@Component({
  selector: 'app-movie-review-list',
  templateUrl: './movie-review-list.component.html',
  styleUrls: ['./movie-review-list.component.css']
})
export class MovieReviewListComponent {
  
  movieName =  "";
  param$! :  Subscription;
  movies! : Review[];
  currentIndex!: number;

  constructor(private activatedRoute: ActivatedRoute, 
    private mrSvc: MovieReviewService, private router: Router){

  }

  ngOnInit(): void {
    this.param$ = this.activatedRoute.params.subscribe(
      async (params) => {
        this.movieName = params['movieName'];
        console.log(this.movieName);
        const l = await this.mrSvc.getReviews(this.movieName);
        console.log(l);
        if (l === undefined || l.length == 0) {
          this.router.navigate(['/'])
        }else{
            this.movies = l;
        }
        
      }
    );

  }

  async previous(){
    console.log(this.currentIndex);
    if(this.currentIndex > 0){
      this.currentIndex = this.currentIndex + 20;
      const l = await this.mrSvc
            .getReviews(this.movieName);
      this.movies = l;
    }
  }

  async toComment(){
    console.log(this.currentIndex);
    const l = await this.mrSvc
          .getReviews(this.movieName);
    this.movies = l;
    this.router.navigate(['comment'])
  }


  ngOnDestroy(): void{
    console.log("destroy sub");
    this.param$.unsubscribe();
  }
}
