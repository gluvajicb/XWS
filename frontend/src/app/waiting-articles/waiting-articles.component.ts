import { Component, OnInit } from '@angular/core';
import { ProcessService } from '../services/process.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-waiting-articles',
  templateUrl: './waiting-articles.component.html',
  styleUrls: ['./waiting-articles.component.css']
})
export class WaitingArticlesComponent implements OnInit {

  constructor(private ProcessService: ProcessService, private router: Router) { }
  articles: JSON[];
  status: string;
  ngOnInit(): void {
    this.status = "reviewing";
    this.articles = [];
    this.getArticles();
  }

  view(id:string) {
    this.router.navigate(['/view/article/' + id]);
  }

  createAndOpenFile(){
    this.ProcessService.getArticlesForUser().subscribe(
      result => {
        var pom = document.createElement('a');

        var filename = "article.xml";
        var pom = document.createElement('a');
        var bb = new Blob([result], {type: 'text/plain'});

        pom.setAttribute('href', window.URL.createObjectURL(bb));
        pom.setAttribute('download', filename);

        pom.dataset.downloadurl = ['text/plain', pom.download, pom.href].join(':');
        pom.draggable = true; 
        pom.classList.add('dragout');

        pom.click();

      });
}
  review(id:string) {
    this.ProcessService.findReviewByArticleId(id).subscribe(
      result => {
        console.log(result)
      }
    );
  }

  getArticles() {

    this.ProcessService.getArticlesForUser().subscribe(
      result => {
        console.log(this.articles);

        /* */
        let parseString = require('xml2js').parseString;
        let i;
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
              "keywords" : keywords,
              "id": result1["ns1:article"]["$"]["ns1:ID"]

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
