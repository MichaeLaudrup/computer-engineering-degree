
import { animate, group, query, stagger, state, style, transition, trigger } from '@angular/animations';
export const StoreRouterAnimation =     trigger('StoreRouterAnimation', [
    transition('section_meals => *', [
      query(' .img-target, .empty__section', [
        style({transform: 'scale(1.2)'}),
        animate('.5s', style({transform: 'scale(0)'}))
      ], {optional: true}),
    ]),
    transition('* => meals', [
      query('.list-meals__list li', [
        style({
          transform: 'translateX(-100%)',
          opacity: 0
        }),
        stagger('.2s', [ animate('.5s', style({
          transform: 'translateX(0%)',
          opacity: 1
        }))])
       ], {optional: true}),
    ])
  ])