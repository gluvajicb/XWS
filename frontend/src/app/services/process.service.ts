import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  private baseUrl = 'http://localhost:8000/';
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});
  private headers_json = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  add(newProcess: string): Observable<any> {
    return this.http.post('http://localhost:8000/process/create', newProcess, {headers: this.headers, responseType: 'text'});
  }


}
