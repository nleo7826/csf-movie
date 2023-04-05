import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Review } from '../model/review';
import { Comment } from '../model/comment';

@Injectable({
  providedIn: 'root'
})
export class MovieReviewService {
  private API_URI: string = "/api/list";

  constructor(private httpClient: HttpClient) { }

  getReviews(movieName: string): Promise<any>{
    const params = new HttpParams()
        .set("movieName", movieName);

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    return lastValueFrom(this.httpClient
        .get<Review[]>(this.API_URI, {params: params, headers: headers}));
  }


//   getMovieDetails(movieId: string): Promise<any>{
//     const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

//     return lastValueFrom(this.httpClient
//         .get<Review[]>(this.API_URI+"/" + movieId, {headers: headers}));
//   }

  saveComment(c:Comment) : Promise<any>{
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body=JSON.stringify(c);
    console.log("save comment !");
    return lastValueFrom(this.httpClient.post<Comment>(this.API_URI+"/" + c.id, body, {headers: headers}));
  }

//   getCharComments(charId: string): Promise<Comment[]>{
//     const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
//     console.log("get all comments !");
//     return lastValueFrom(this.httpClient
//         .get<Comment[]>(this.API_URI+"/comments/" + charId, {headers: headers}));
//   }

}
