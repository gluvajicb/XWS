import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { ArticleService } from '../services/article.service';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { SearchParams } from '../model/searchArticle'
import { FormGroup, FormBuilder } from '@angular/forms';
import {} from 'xml2js'

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchParams: SearchParams;
  articles: JSON[];
  constructor(private router: Router,
              private ArticleService: ArticleService,
              private fb: FormBuilder) {

    this.searchParams = new SearchParams();
  }

  ngOnInit() {
    this.articles = []
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
        let parseString = require('xml2js').parseString;
        let i;
        this.articles.splice(0, this.articles.length)
        for (i = 0; i < result.length; i++) {
          console.log(result[i]);
          let res;
          let authors = [];
          let keywords = [];
          
          parseString(result[i], function (err, result1) {
            
            console.log(result1)

            let j, k;
            let authors = []
            for(j = 0; j < result1["ns1:article"]["ns1:authors"][0]["ns1:author"].length; j ++){

              authors[j] = result1["ns1:article"]["ns1:authors"][0]["ns1:author"][j]["ns1:name"] + " " + result1["ns1:article"]["ns1:authors"][0]["ns1:author"][j]["ns1:surname"];
            }
            if(result1["ns1:article"]["ns1:abstract"][0]["ns1:keywords"] !== undefined) {
              for(k = 0; k < result1["ns1:article"]["ns1:abstract"][0]["ns1:keywords"].length; k++){

                keywords[k] = result1["ns1:article"]["ns1:abstract"][0]["ns1:keywords"][k]["ns1:keyword"];
              }
            }
            
            let retVal = {
              "title": result1["ns1:article"]["ns1:title"],
              "authors": authors,
              "keywords" : keywords
            }
            res = retVal
          });

          this.articles[i] = res
        }

        console.log(this.articles)
        console.log(this.articles[0]["ns1:article"]["ns1:abstract"][0]["ns1:keywords"][0]["ns1:keyword"][0])
        let a = this.articles[0];
        console.log(a["ns1:article"]["ns1:authors"])
      }
    );
    

  }
}
