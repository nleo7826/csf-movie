import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchReviewComponent } from './component/search-review.component';
import { MovieReviewListComponent } from './component/movie-review-list.component';
import { PostCommentComponent } from './component/post-comment.component';

const routes: Routes = [
  {path:'', component:SearchReviewComponent },
  {path: 'list/:movieName', component: MovieReviewListComponent},
  {path: 'comment', component: PostCommentComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
