import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit, OnDestroy{
  form!: FormGroup;
  movieName?: string;

  constructor(private formBuilder: FormBuilder, private router: Router,
    ){
  
  }

  ngOnInit(): void {
    this.form = this.createForm();
  }

  ngOnDestroy(): void {
      
  }

  search(){
    //remove trailing and leading spaces
    const movieName = this.form?.value['movieName'].trim();
    this.router.navigate(['/list', movieName]);
  }

  private createForm(): FormGroup{
    return this.formBuilder.group({
      movieName : this.formBuilder.control(''),  
    })
  }
}
