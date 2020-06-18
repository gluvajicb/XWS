import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { ArticleService } from '../services/article.service';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { SearchParams } from '../model/searchArticle'
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchParams: SearchParams;

  constructor(private router: Router,
              private ArticleService: ArticleService,
              private fb: FormBuilder) {

    this.searchParams = new SearchParams();

  }

  ngOnInit() {

  }

  search() {

    var searchParams_JSON =
                     {
                         "abst": "" + this.searchParams.abst,
                         "title": "" +  this.searchParams.title,
                         "keyword": "" +  this.searchParams.keyword,
                         "author": "" +  this.searchParams.author,
                         "section": "" +  this.searchParams.section
                     };

    console.log(searchParams_JSON)

    this.ArticleService.searchArticle(searchParams_JSON).subscribe(
      result => {
        this.router.navigate(['home']);
      }
    );
  }
}
