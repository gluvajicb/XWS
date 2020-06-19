import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { ArticleService } from '../services/article.service';
import { ActivatedRoute } from '@angular/router';


declare var Xonomy: any;


@Component({
  selector: 'app-edit-article',
  templateUrl: './edit-article.component.html',
  styleUrls: ['./edit-article.component.css']
})
export class EditArticleComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  private id: any;
  private sub: any;

  constructor(private ArticleService: ArticleService,
              private route: ActivatedRoute,
              private router: Router) { }

  docSpec = {
    
    
    elements: {
      
      article: {

      },

      title: {
        hasText: true
      },

      publisher: {
        hasText: true
      },

      keywords: {
        menu: [{
          caption: 'Append a <keyword>',
          action: Xonomy.newElementChild,
          actionParameter: '<keyword></keyword>'
          }]
      },
      keyword: {
        hasText: true,
        menu: [{
          caption: 'Delete this <keyword>',
          action: Xonomy.deleteElement
          }, {
            caption: 'New <keyword> before this',
            action: Xonomy.newElementBefore,
            actionParameter: '<keyword/>'
            }, {
            caption: 'New <keyword> after this',
            action: Xonomy.newElementAfter,
            actionParameter: '<keyword/>'
            }
        ]
      },
      abstract: {
        menu: [{
          caption: 'Append a <paragraph>',
          action: Xonomy.newElementChild,
          actionParameter: '<paragraph></paragraph>'
          }, {
          caption: 'Append a <keywords>',
          action: Xonomy.newElementChild,
          actionParameter: '<keywords></keywords>'
          }]
      },
      sections:{
        menu: [{
          caption: 'Append a <section>',
          action: Xonomy.newElementChild,
          actionParameter: '<section></section>'
        }]
      },
      section:{
        menu: [{
          caption: 'Append a <title>',
          action: Xonomy.newElementChild,
          actionParameter: '<title></title>'
        }, {
          caption: 'Append a <paragraph>',
          action: Xonomy.newElementChild,
          actionParameter: '<paragraph></paragraph>'
        }
        ]
      },
      authors: {
        menu: [{
          caption: 'Append an <author>',
          action: Xonomy.newElementChild,
          actionParameter: '<author></author>'
        }]
      },
      author:{
        menu: [{
          caption: 'Append a <name>',
          action: Xonomy.newElementChild,
          actionParameter: '<name></name>'
        },
        {
          caption: 'Append a <surname>',
          action: Xonomy.newElementChild,
          actionParameter: '<surname></surname>'
        },
        {
          caption: 'Append an <university>',
          action: Xonomy.newElementChild,
          actionParameter: '<university></university>'
        }
        ]
      },
      name:{
          hasText: true,
          menu: [
            {
              caption: 'Delete this <name>',
              action: Xonomy.deleteElement
            }
          ]
      },
      surname:{
        hasText: true,
        menu: [
          {
            caption: 'Delete this <surname>',
            action: Xonomy.deleteElement
          }
        ]
      },
      university:{
        menu: [
          {
            caption: 'Append a <name>',
            action: Xonomy.newElementChild,
            actionParameter: '<name></name>'
          },
          {
            caption: 'Append an <address>',
            action: Xonomy.newElementChild,
            actionParameter: '<address></address>'
          }
        ]
      },
      address:{
        hasText: true,
        menu: [
          {
            caption: 'Delete this <surname>',
            action: Xonomy.deleteElement
          }
        ]
      },
      paragraph: {
        menu: [
          {
            caption: 'Append a <text>',
            action: Xonomy.newElementChild,
            actionParameter: '<text></text>'
          },
          {
            caption: 'Delete this <paragraph>',
            action: Xonomy.deleteElement
          },
          {
            caption: 'New <paragraph> before this',
            action: Xonomy.newElementBefore,
            actionParameter: '<paragraph/>'
          }, {
            caption: 'New <paragraph> after this',
            action: Xonomy.newElementAfter,
            actionParameter: '<paragraph/>'
          }
        ]
      },
      text: {
        hasText: true
      },
      new_line: {
      }
    }
  };


  ngOnInit() {
    this.start();
  }

  start() {

    this.sub = this.route.params.subscribe(params => {
        this.id = params['id'];
    });

    
    let xml = '<article xmlns="http://www.uns.ac.rs/XMLtim/Article" xmlns:msb="http://www.uns.ac.rs/XMLtim/Article">' + 
                  '<title>' +
                  '</title>' +
                  '<publisher>' + 
                  '</publisher>' +
                  '<authors>' +
                  '</authors>' +
                  '<abstract>' +
                  '</abstract>' +
                  '<sections>' +
                  '</sections>' +
                '</article>';

    this.ArticleService.getArticle(this.id).subscribe(
      result => {
        xml = result;

        const editor = document.getElementById('editor');
        Xonomy.render(xml.split("ns1:").join(""), editor, this.docSpec);
      }
    );

  }

  submit() {
  
  
    const xml = Xonomy.harvest();

    console.log(xml);
    xml.split("<").join("<ns1:")
    this.ArticleService.update(xml as string, this.id).subscribe(
      result => {
        this.router.navigate(['/my-articles']);
      }
    );
  }
  

  
}
