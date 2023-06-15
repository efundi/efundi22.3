declare const LionCalendar_base: typeof LitElement & import("@open-wc/dedupe-mixin").Constructor<import("@lion/localize/types/LocalizeMixinTypes").LocalizeMixinHost> & Pick<typeof import("@lion/localize/types/LocalizeMixinTypes").LocalizeMixinHost, "prototype" | "localizeNamespaces" | "waitForLocalizeNamespaces"> & Pick<typeof LitElement, "prototype" | "render" | "styles" | "shadowRootOptions" | "getStyles" | "properties" | "observedAttributes" | "createProperty">;
/**
 * @typedef {import('../types/day').Day} Day
 * @typedef {import('../types/day').Week} Week
 * @typedef {import('../types/day').Month} Month
 */
/**
 * @customElement lion-calendar
 */
export class LionCalendar extends LionCalendar_base {
    static get properties(): {
        /**
         * Minimum date. All dates before will be disabled
         */
        minDate: {
            attribute: boolean;
        };
        /**
         * Maximum date. All dates after will be disabled
         */
        maxDate: {
            attribute: boolean;
        };
        /**
         * Disable certain dates
         */
        disableDates: {
            attribute: boolean;
        };
        /**
         * The selected date, usually synchronized with datepicker-input
         * Not to be confused with the focused date (therefore not necessarily in active month view)
         */
        selectedDate: {
            attribute: boolean;
        };
        /**
         * The date that
         * 1. determines the currently visible month
         * 2. will be focused when the month grid gets focused by the keyboard
         */
        centralDate: {
            attribute: boolean;
        };
        /**
         * Weekday that will be displayed in first column of month grid.
         * 0: sunday, 1: monday, 2: tuesday, 3: wednesday , 4: thursday, 5: friday, 6: saturday
         * Default is 0
         */
        firstDayOfWeek: {
            attribute: boolean;
        };
        /**
         * Weekday header notation, based on Intl DatetimeFormat:
         * - 'long' (e.g., Thursday)
         * - 'short' (e.g., Thu)
         * - 'narrow' (e.g., T).
         * Default is 'short'
         */
        weekdayHeaderNotation: {
            attribute: boolean;
        };
        /**
         * Different locale for this component scope
         */
        locale: {
            attribute: boolean;
        };
        /**
         * The currently focused date (if any)
         */
        __focusedDate: {
            attribute: boolean;
        };
        /**
         * Data to render current month grid
         */
        __data: {
            attribute: boolean;
        };
    };
    static get styles(): import("@lion/core").CSSResult[];
    /** @type {{months: Month[]}}
     * @private
     */
    private __data;
    minDate: Date;
    maxDate: Date;
    /** @param {Day} day */
    dayPreprocessor: (day: Day) => import("../types/day.js").Day;
    /** @param {Date} day */
    disableDates: (day: Date) => boolean;
    firstDayOfWeek: number;
    weekdayHeaderNotation: string;
    /** @private */
    private __today;
    /** @type {Date} */
    centralDate: Date;
    /**
     * @type {Date | null}
     * @private
     */
    private __focusedDate;
    /** @private */
    private __connectedCallbackDone;
    /** @private */
    private __eventsAdded;
    locale: string;
    /** @private */
    private __boundKeyboardNavigationEvent;
    /** @private */
    private __boundClickDateDelegation;
    /** @private */
    private __boundFocusDateDelegation;
    /** @private */
    private __boundBlurDateDelegation;
    get focusedDate(): Date | null;
    goToNextMonth(): void;
    goToPreviousMonth(): void;
    goToNextYear(): void;
    goToPreviousYear(): void;
    /**
     * @param {Date} date
     */
    focusDate(date: Date): Promise<void>;
    focusCentralDate(): void;
    focusSelectedDate(): Promise<void>;
    __contentWrapperElement: HTMLButtonElement | undefined;
    /**
     * This exposes an interface for datepickers that want to
     * reinitialize when calendar is opened
     */
    initCentralDate(): void;
    /**
     * @private
     */
    private __calculateInitialCentralDate;
    /** @type {Date} */
    __initialCentralDate: Date | undefined;
    /**
     * @param {string} month
     * @param {number} year
     * @private
     */
    private __renderMonthNavigation;
    /**
     * @param {string} month
     * @param {number} year
     * @private
     */
    private __renderYearNavigation;
    /**
     * @private
     */
    private __renderNavigation;
    /**
     * @private
     */
    private __renderData;
    /**
     * @param {string} type
     * @param {string} previousMonth
     * @param {number} previousYear
     * @private
     */
    private __getPreviousDisabled;
    /**
     * @param {string} type
     * @param {string} nextMonth
     * @param {number} nextYear
     * @private
     */
    private __getNextDisabled;
    /**
     * @param {string} type
     * @param {string} previousMonth
     * @param {number} previousYear
     * @protected
     */
    protected __renderPreviousButton(type: string, previousMonth: string, previousYear: number): import("@lion/core").TemplateResult;
    /**
     * @param {string} type
     * @param {string} nextMonth
     * @param {number} nextYear
     * @protected
     */
    protected __renderNextButton(type: string, nextMonth: string, nextYear: number): import("@lion/core").TemplateResult;
    /**
     *
     * @param {string} mode
     * @param {string} type
     * @param {string} month
     * @param {number} year
     * @private
     */
    private __getNavigationLabel;
    /**
     *
     * @param {Day} _day
     * @param {*} param1
     * @private
     */
    private __coreDayPreprocessor;
    /**
     * @param {Day} [options]
     * @private
     */
    private __createData;
    /**
     * @private
     */
    private __disableDatesChanged;
    /**
     * @param {Date} selectedDate
     * @private
     */
    private __dateSelectedByUser;
    selectedDate: Date | undefined;
    /**
     * @private
     */
    private __centralDateChanged;
    /**
     * @private
     */
    private __focusedDateChanged;
    /**
     * @private
     */
    private __ensureValidCentralDate;
    /**
     * @param {Date} date
     * @private
     */
    private __isEnabledDate;
    /**
     * @param {Date} date
     * @param {Object} opts
     * @param {String} [opts.mode] Find best date in `future/past/both`
     * @private
     */
    private __findBestEnabledDateFor;
    /**
     * @param {Event} ev
     * @private
     */
    private __clickDateDelegation;
    /**
     * @private
     */
    private __focusDateDelegation;
    /**
     * @private
     */
    private __blurDateDelegation;
    /**
     * @param {KeyboardEvent} ev
     * @private
     */
    private __keyboardNavigationEvent;
    /**
     *
     * @param {number} modify
     * @param {Object} opts
     * @param {string} opts.dateType
     * @param {string} opts.type
     * @param {string} opts.mode
     * @private
     */
    private __modifyDate;
    /**
     * @private
     */
    private __getLocale;
}
export type Day = import("../types/day.js").Day;
export type Week = import("../types/day.js").Week;
export type Month = import("../types/day.js").Month;
import { LitElement } from "@lion/core";
export {};
