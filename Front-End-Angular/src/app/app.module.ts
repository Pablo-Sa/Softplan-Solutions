import { MaterialModule } from "./material.module";
import { BrowserModule } from "@angular/platform-browser";
import { LOCALE_ID, NgModule } from "@angular/core";
import ptBr  from "@angular/common/locales/pt";
registerLocaleData(ptBr)
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";
import { Interceptor } from "./interceptors/interceptor.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { LoginComponent } from "./components/login/login.component";
import {
  BreakPointRegistry,
  FlexLayoutModule,
  FlexOrderStyleBuilder,
  FlexStyleBuilder,
  LayoutAlignStyleBuilder,
  LayoutStyleBuilder,
  MediaMarshaller,
  PrintHook,
  ShowHideStyleBuilder,
  StylesheetMap,
  StyleUtils,
  ɵMatchMedia,
} from "@angular/flex-layout";
import { HomeComponent } from "./components/home/home.component";
import { HeaderComponent } from "./components/header/header.component";
import { FooterComponent } from "./components/footer/footer.component";
import { PeopleComponent } from "./components/people/people.component";
import { SearchPeopleComponent } from "./components/search-people/search-people.component";
import { LogPeopleComponent } from "./components/log-people/log-people.component";
import { DatePickerPeopleComponent } from "./components/date-picker-people/date-picker-people.component";
import { EditModalPeopleComponent } from "./components/modals/edit-modal-people/edit-modal-people.component";
import { ExclusionModalPeopleComponent } from "./components/modals/exclusion-modal-people/exclusion-modal-people.component";
import { GitUrlModalComponent } from "./components/modals/git-url-modal/git-url-modal.component";
import { registerLocaleData } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ChatModalComponent } from './components/modals/chat-modal/chat-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    PeopleComponent,
    SearchPeopleComponent,
    LogPeopleComponent,
    DatePickerPeopleComponent,
    EditModalPeopleComponent,
    ExclusionModalPeopleComponent,
    GitUrlModalComponent,
    ChatModalComponent
  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    MaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    Interceptor,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
  ],
  providers: [
    StyleUtils,
    LayoutAlignStyleBuilder,
    StylesheetMap,
    MediaMarshaller,
    ɵMatchMedia,
    BreakPointRegistry,
    PrintHook,
    LayoutStyleBuilder,
    FlexStyleBuilder,
    ShowHideStyleBuilder,
    FlexOrderStyleBuilder,
    { provide: LOCALE_ID, useValue: "pt-BR" },
  ],
  entryComponents: [
    DatePickerPeopleComponent,
    ExclusionModalPeopleComponent,
    EditModalPeopleComponent,
    GitUrlModalComponent,
    ChatModalComponent
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
