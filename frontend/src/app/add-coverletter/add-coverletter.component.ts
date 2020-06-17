import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { CoverletterService } from '../services/coverletter.service';

declare var Xonomy: any;

@Component({
  selector: 'app-add-coverletter',
  templateUrl: './add-coverletter.component.html',
  styleUrls: ['./add-coverletter.component.css']
})
export class AddCoverletterComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private CoverLetterService: CoverletterService,
              private router: Router) { }


  docSpec = {

    elements: {

      coverLetter: {

      },
      title: {
        hasText: true
      },
      articleId: {
        hasText: true
      },
      submission_date: {
        hasText: true
      },
      text: {
        hasText: true
      }
    
    }

  };

  ngOnInit() {
    this.start();
  }

  start() {

      const xml = '<coverLetter>' +
                      '<title></title>' +
                      '<articleId></articleId>' +
                      '<submission_date></submission_date>' +
                      '<text></text>' +
                  '</coverLetter>'

      const editor = document.getElementById('editor');
      Xonomy.render(xml, editor, this.docSpec);

  }

  submit() {

    const xml = Xonomy.harvest();

    console.log(xml);
    this.CoverLetterService.add(xml as string).subscribe(
        result => {
          this.router.navigate(['home']);
        }
    )
  }

}
