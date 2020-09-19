import { Observable } from "rxjs";
import { PeopleInterface } from "./../models/people-interface";
import { environment } from "./../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class PeopleServiceService {
  private serverAPI: string = `${environment.API}/people`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<PeopleInterface[]> {
    return this.http.get<PeopleInterface[]>(this.serverAPI);
  }

  post(people: PeopleInterface): Observable<PeopleInterface> {
    return this.http.post<PeopleInterface>(this.serverAPI, people);
  }
}
